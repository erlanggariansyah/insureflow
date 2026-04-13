package com.openlife.product.service;

import com.openlife.product.constant.AttributeConstant;
import com.openlife.product.dto.request.ProductConfigSyncRequest;
import com.openlife.product.entity.Product;
import com.openlife.product.entity.ProductRider;
import com.openlife.product.entity.ProductType;
import com.openlife.product.entity.Rider;
import com.openlife.product.entity.Rule;
import com.openlife.product.repository.ProductRepository;
import com.openlife.product.repository.ProductRiderRepository;
import com.openlife.product.repository.ProductTypeRepository;
import com.openlife.product.repository.RiderRepository;
import com.openlife.product.repository.RuleRepository;
import com.openlife.product.repository.ProductRateRepository;
import com.openlife.product.entity.ProductRate;
import com.openlife.product.entity.RiderType;
import com.openlife.product.entity.Gender;
import com.openlife.product.entity.PaymentFrequency;
import com.openlife.product.entity.RuleType;
import com.openlife.product.repository.RiderTypeRepository;
import com.openlife.product.repository.GenderRepository;
import com.openlife.product.repository.PaymentFrequencyRepository;
import com.openlife.product.repository.RuleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRiderRepository productRiderRepository;
    private final RiderRepository riderRepository;
    private final RuleRepository ruleRepository;
    private final ProductRateRepository productRateRepository;
    private final RiderTypeRepository riderTypeRepository;
    private final GenderRepository genderRepository;
    private final PaymentFrequencyRepository paymentFrequencyRepository;
    private final RuleTypeRepository ruleTypeRepository;

    @Cacheable(value = "productTypes")
    public List<ProductType> getProductTypes() {
        return productTypeRepository.findAll();
    }

    public List<Product> getProductsByProductTypeId(UUID productTypeId) {
        return productRepository.findByProductTypeIdAndIsActiveTrue(productTypeId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }

    public List<ProductRate> getAllRates() {
        return productRateRepository.findAll();
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public List<RiderType> getAllRiderTypes() {
        return riderTypeRepository.findAll();
    }

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public List<PaymentFrequency> getAllPaymentFrequencies() {
        return paymentFrequencyRepository.findAll();
    }

    public List<RuleType> getAllRuleTypes() {
        return ruleTypeRepository.findAll();
    }

    public java.math.BigDecimal lookupRate(UUID productId, Integer age, Integer genderId) {
        return productRateRepository.findRate(productId, age, genderId);
    }

    public List<Rider> getRidersByProductId(UUID productId) {
        List<ProductRider> productRiders = productRiderRepository.findByProductId(productId);
        List<Rider> riders = new ArrayList<>();

        for (ProductRider productRider : productRiders) {
            Optional<Rider> rider = riderRepository.findByIdAndIsActiveTrue(productRider.getRiderId());
            rider.ifPresent(riders::add);
        }

        return riders;
    }

    public boolean validate(String expression, Object data) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable(AttributeConstant.INPUT, data);

        return Boolean.TRUE.equals(parser.parseExpression(expression).getValue(context, Boolean.class));
    }

    @Transactional
    public void syncProductConfig(ProductConfigSyncRequest request) {
        if (request.getProduct() == null) return;

        // 1. Save or Update Product
        Product product = new Product();
        if (request.getProduct().getId() != null) {
            product = productRepository.findById(request.getProduct().getId()).orElse(new Product());
            if (product.getId() == null) {
                product.setId(request.getProduct().getId());
            }
        }
        product.setCode(request.getProduct().getCode());
        product.setName(request.getProduct().getName());
        if (request.getProduct().getProductTypeId() != null) {
            product.setProductTypeId(request.getProduct().getProductTypeId());
        }
        product.setIsActive(true);
        product = productRepository.save(product);
        UUID finalProductId = product.getId();

        // 2. Save or Update Riders and ProductRider relations
        if (request.getRiders() != null) {
            for (ProductConfigSyncRequest.RiderDto rd : request.getRiders()) {
                Rider rider = new Rider();
                if (rd.getId() != null) {
                    rider = riderRepository.findById(rd.getId()).orElse(new Rider());
                    if (rider.getId() == null) {
                        rider.setId(rd.getId());
                    }
                }
                rider.setCode(rd.getCode());
                rider.setName(rd.getName());
                if (rd.getRiderTypeId() != null) {
                    rider.setRiderTypeId(rd.getRiderTypeId());
                }
                rider.setIsActive(true);
                rider = riderRepository.save(rider);

                // Create relationship
                ProductRider pr = new ProductRider();
                pr.setProductId(finalProductId);
                pr.setRiderId(rider.getId());
                pr.setIsMandatory(rd.getIsMandatory() != null ? rd.getIsMandatory() : false);
                productRiderRepository.save(pr);
            }
        }

        // 3. Save or Update Rules
        if (request.getRules() != null) {
            for (ProductConfigSyncRequest.RuleDto ruleDto : request.getRules()) {
                Rule rule = new Rule();
                if (ruleDto.getId() != null) {
                    rule = ruleRepository.findById(ruleDto.getId()).orElse(new Rule());
                    if (rule.getId() == null) {
                        rule.setId(ruleDto.getId());
                    }
                }
                rule.setSubId(ruleDto.getSubId() != null ? ruleDto.getSubId() : finalProductId);
                rule.setName(ruleDto.getName());
                rule.setExpression(ruleDto.getExpression());
                rule.setMessage(ruleDto.getMessage());
                // rule.setType(ruleDto.getTypeId()); // mapping rule type if needed (currently entity requires RuleType object)
                ruleRepository.save(rule);
            }
        }
    }
}
