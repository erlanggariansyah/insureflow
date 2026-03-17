package com.insureflow.policy.constant;

public class PolicyConstant {
    private PolicyConstant() {}

    public static final String POLICY = "policy";
    public static final String STATUS_SUBMITTED = "SUBMITTED";

    public static String generatePolicyNumber() {
        return "INSURE-" + System.currentTimeMillis();
    }
}
