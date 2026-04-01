package com.openlife.product.controller;

import com.openlife.product.constant.AttributeConstant;
import com.openlife.product.constant.MessageConstant;
import com.openlife.product.constant.PathConstant;
import com.openlife.product.constant.URIConstant;
import com.openlife.product.entity.Product;
import com.openlife.product.entity.ProductType;
import com.openlife.product.entity.Rider;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
