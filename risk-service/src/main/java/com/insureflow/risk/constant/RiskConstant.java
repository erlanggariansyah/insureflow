package com.insureflow.risk.constant;

public class RiskConstant {
    private RiskConstant() {}

    public static final String LEVEL_HIGH = "HIGH";
    public static final String LEVEL_MEDIUM = "MEDIUM";
    public static final String LEVEL_LOW = "LOW";

    public static final int highThreshold = 500;
    public static final int mediumThreshold = 200;
    public static final double highScore = 0.9;
    public static final double mediumScore = 0.6;
    public static final double lowScore = 0.3;
}
