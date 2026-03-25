package com.insureflow.policy.constant;

public class MessageConstant {
    private MessageConstant() {}

    public static final String POLICY_NOT_FOUND = "policy not found";
    public static final String CUSTOMER_NAME_MANDATORY = "Customer name is required";

    public static final String AGE_MANDATORY = "Age is required";
    public static final String AGE_MIN = "Minimum age is 18";
    public static final String AGE_MAX = "Maximum age is 100";

    public static final String AGENT_ID_MANDATORY = "Agent ID is required";

    public static final String PREMIUM_MANDATORY = "Premium is required";
    public static final String PREMIUM_POSITIVE = "Premium must be greater than zero";

    public static final String SUM_ASSURED_MANDATORY = "Sum assured is required";
    public static final String SUM_ASSURED_POSITIVE = "Sum assured must be greater than zero";

    public static final String POLICY_CREATION_SUCCESS = "Policy created successfully";
    public static final String POLICY_RETRIEVE_SUCCESS = "Policy retrieved successfully";

    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String DATABASE_ERROR = "Database error occurred";
    public static final String INVALID_REQUEST = "Invalid request";

    public static final String EVENT_PUBLISH_FAILED = "Event publish failed";
    public static final String EVENT_PUBLISH_SUCCESS = "Event publish success to partition {} offset {}";

    public static final String ERROR_DETAILS_PLACEHOLDER = "Error details: ";
}
