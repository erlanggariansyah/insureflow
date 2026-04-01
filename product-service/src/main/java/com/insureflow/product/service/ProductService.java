package com.insureflow.product.service;

import com.insureflow.product.entity.Product;
import com.insureflow.product.entity.ProductRider;
import com.insureflow.product.entity.ProductType;
import com.insureflow.product.entity.Rider;
import com.insureflow.product.repository.ProductRepository;
import com.insureflow.product.repository.ProductRiderRepository;
import com.insureflow.product.repository.ProductTypeRepository;
import com.insureflow.product.repository.RiderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

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

    @Cacheable(value = "productTypes")
    public List<ProductType> getProductTypes() {
        return productTypeRepository.findAll();
    }

    public List<Product> getProductsByProductTypeId(UUID productTypeId) {
        return productRepository.findByProductTypeIdAndIsActiveTrue(productTypeId);
    }

    public List<Rider> getRidersByProductId(UUID productId) {
        List<ProductRider> productRiders = productRiderRepository.findByProductId(productId);
        List<Rider> riders = new ArrayList<>();

        // ini bukan best practice (N+1 Problem), namun clean
        // Sebenarnya bisa menggunakan join langsung dari product rider by product id ke rider
        for (ProductRider productRider : productRiders) {
            Optional<Rider> rider = riderRepository.findByIdAndIsActiveTrue(productRider.getRiderId());
            riders.add(rider.get());
        }

        return riders;
    }

    public boolean validate(String expression, Object data) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("input", data);

        return Boolean.TRUE.equals(parser.parseExpression(expression).getValue(context, Boolean.class));
    }
}
