package com.insureflow.product.service;

import com.insureflow.product.dto.request.CalculateRequest;
import com.insureflow.product.dto.request.CalculateRiderItemRequest;
import com.insureflow.product.dto.response.CalculateResponse;
import com.insureflow.product.dto.response.CalculateRiderItemResponse;
import com.insureflow.product.entity.*;
import com.insureflow.product.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationService {
    private final ProductRepository productRepository;
    private final ProductRateRepository productRateRepository;
    private final RiderRepository riderRepository;
    private final RiderRateRepository riderRateRepository;
    private final PaymentFrequencyRepository paymentFrequencyRepository;

    public CalculateResponse calculate(CalculateRequest request) {
        Product product = productRepository.findByCode(request.getCode()).get();
        PaymentFrequency freq = paymentFrequencyRepository.findById(request.getPaymentFrequencyId()).get();

        BigDecimal productRate = productRateRepository.findRate(
                product.getId(), request.getAge(), request.getGender()
        );

        BigDecimal annualBasePremium = request
                .getSumAssured()
                .divide(BigDecimal.valueOf(1000), 10, RoundingMode.HALF_UP)
                .multiply(productRate);

        BigDecimal periodicBasePremium = annualBasePremium.multiply(freq.getFactor()).setScale(2, RoundingMode.HALF_UP);

        List<CalculateRiderItemResponse> riderResponses = new ArrayList<>();
        BigDecimal totalRiderPremium = BigDecimal.ZERO;

        if (request.getRiders() != null) {
            for (CalculateRiderItemRequest riderReq : request.getRiders()) {
                Rider rider = riderRepository.findByCode(riderReq.getCode()).get();

                BigDecimal rRate = riderRateRepository.findRate(
                        rider.getId(), request.getAge(), request.getGender()
                );

                BigDecimal riderPremium = calculateRiderByMethod(
                        rider.getMethodId(), riderReq.getSumAssured(), rRate, periodicBasePremium, freq.getFactor()
                );

                riderResponses.add(
                        CalculateRiderItemResponse.builder()
                        .code(rider.getCode())
                        .premium(riderPremium)
                        .build()
                );

                totalRiderPremium = totalRiderPremium.add(riderPremium);
            }
        }

        return CalculateResponse.builder()
                .productCode(product.getCode())
                .basePremium(periodicBasePremium)
                .riders(riderResponses)
                .totalPremium(periodicBasePremium.add(totalRiderPremium))
                .build();
    }

    private BigDecimal calculateRiderByMethod(Integer method, BigDecimal sa, BigDecimal rate, BigDecimal basePremium, BigDecimal factor) {
        return switch (method) {
            case 1 -> rate
                    .multiply(factor).setScale(2, RoundingMode.HALF_UP);
            case 2 -> sa
                    .divide(BigDecimal.valueOf(1000), 10, RoundingMode.HALF_UP)
                    .multiply(rate).multiply(factor).setScale(2, RoundingMode.HALF_UP);
            case 3 -> basePremium
                    .multiply(rate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP))
                    .setScale(2, RoundingMode.HALF_UP);
            case 4 -> sa
                    .multiply(rate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP))
                    .multiply(factor).setScale(2, RoundingMode.HALF_UP);
            default -> BigDecimal.ZERO;
        };
    }
}
