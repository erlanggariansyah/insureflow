package com.openlife.product.controller;

import com.openlife.product.constant.AttributeConstant;
import com.openlife.product.constant.MessageConstant;
import com.openlife.product.constant.PathConstant;
import com.openlife.product.constant.URIConstant;
import com.openlife.product.entity.Product;
import com.openlife.product.entity.ProductType;
import com.openlife.product.entity.Rider;
import com.openlife.product.entity.Rule;
import com.openlife.product.entity.ProductRate;
import com.openlife.product.entity.RiderType;
import com.openlife.product.entity.Gender;
import com.openlife.product.entity.PaymentFrequency;
import com.openlife.product.entity.RuleType;
import com.openlife.product.exception.constraint.annotation.ExistProductId;
import com.openlife.product.exception.constraint.annotation.ExistProductTypeId;
import com.openlife.product.service.ProductService;
import com.openlife.product.util.APIResponse;
import com.openlife.product.util.TraceId;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.openlife.product.dto.request.ProductConfigSyncRequest;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(PathConstant.PRODUCTS_V1)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(URIConstant.TYPES)
    public APIResponse<List<ProductType>> getProductTypes() {
        List<ProductType> response = productService.getProductTypes();
        return APIResponse.<List<ProductType>>builder()
                .message(MessageConstant.PRODUCT_TYPE_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping
    public APIResponse<List<Product>> getProductsByProductTypeId(
            @RequestParam(AttributeConstant.PRODUCT_TYPE_ID)
            @NotNull(message = MessageConstant.PRODUCT_TYPE_ID_FIELD_MANDATORY)
            @ExistProductTypeId UUID productTypeId
    ) {
        List<Product> response = productService.getProductsByProductTypeId(productTypeId);
        return APIResponse.<List<Product>>builder()
                .message(MessageConstant.PRODUCT_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.ALL)
    public APIResponse<List<Product>> getAllProducts() {
        List<Product> response = productService.getAllProducts();
        return APIResponse.<List<Product>>builder()
                .message(MessageConstant.PRODUCT_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RIDERS)
    public APIResponse<List<Rider>> getRidersByProductId(
            @RequestParam(AttributeConstant.PRODUCT_ID)
            @NotNull(message = MessageConstant.PRODUCT_ID_FIELD_MANDATORY)
            @ExistProductId UUID productId
    ) {
        List<Rider> response = productService.getRidersByProductId(productId);
        return APIResponse.<List<Rider>>builder()
                .message(MessageConstant.RIDER_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RIDERS_ALL)
    public APIResponse<List<Rider>> getAllRiders() {
        List<Rider> response = productService.getAllRiders();
        return APIResponse.<List<Rider>>builder()
                .message(MessageConstant.RIDER_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RATES_ALL)
    public APIResponse<List<ProductRate>> getAllRates() {
        List<ProductRate> response = productService.getAllRates();
        return APIResponse.<List<ProductRate>>builder()
                .message(MessageConstant.RATE_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RULES_ALL)
    public APIResponse<List<Rule>> getAllRules() {
        List<Rule> response = productService.getAllRules();
        return APIResponse.<List<Rule>>builder()
                .message(MessageConstant.RULE_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RIDER_TYPES)
    public APIResponse<List<RiderType>> getAllRiderTypes() {
        List<RiderType> response = productService.getAllRiderTypes();
        return APIResponse.<List<RiderType>>builder()
                .message(MessageConstant.RIDER_TYPE_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.GENDERS)
    public APIResponse<List<Gender>> getAllGenders() {
        List<Gender> response = productService.getAllGenders();
        return APIResponse.<List<Gender>>builder()
                .message(MessageConstant.GENDER_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.PAYMENT_FREQUENCIES)
    public APIResponse<List<PaymentFrequency>> getAllPaymentFrequencies() {
        List<PaymentFrequency> response = productService.getAllPaymentFrequencies();
        return APIResponse.<List<PaymentFrequency>>builder()
                .message(MessageConstant.PAYMENT_FREQUENCY_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RULE_TYPES)
    public APIResponse<List<RuleType>> getAllRuleTypes() {
        List<RuleType> response = productService.getAllRuleTypes();
        return APIResponse.<List<RuleType>>builder()
                .message(MessageConstant.RULE_TYPE_RETRIEVE_SUCCESS)
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @GetMapping(URIConstant.RATES_LOOKUP)
    public APIResponse<java.math.BigDecimal> lookupRate(
            @RequestParam("productId") UUID productId,
            @RequestParam("age") Integer age,
            @RequestParam("gender") Integer gender
    ) {
        java.math.BigDecimal response = productService.lookupRate(productId, age, gender);
        return APIResponse.<java.math.BigDecimal>builder()
                .message("Rate lookup successful")
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .data(response)
                .build();
    }

    @PostMapping("/sync")
    public APIResponse<Void> syncProductConfiguration(
            @RequestBody @NotNull ProductConfigSyncRequest request
    ) {
        productService.syncProductConfig(request);
        return APIResponse.<Void>builder()
                .message("Successfully synced product configuration")
                .status(HttpStatus.OK.value())
                .traceId(TraceId.getTraceId())
                .build();
    }
}
