package com.openlife.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductConfigSyncRequest {
    @NotNull
    private ProductDto product;
    private List<RiderDto> riders;
    private List<RuleDto> rules;

    @Data
    public static class ProductDto {
        private UUID id;
        private String code;
        private String name;
        private UUID productTypeId;
    }

    @Data
    public static class RiderDto {
        private UUID id; // Used to link to existing or create new if not exists
        private String code;
        private String name;
        private UUID riderTypeId;
        private Boolean isMandatory;
    }

    @Data
    public static class RuleDto {
        private UUID id;
        private UUID subId; // ID of the product or rider this rule attaches to
        private String name;
        private String expression;
        private String message;
        private UUID typeId;
    }
}
