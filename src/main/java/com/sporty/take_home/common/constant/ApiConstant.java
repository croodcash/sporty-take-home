package com.sporty.take_home.common.constant;

public final class ApiConstant {
    
    private ApiConstant() {
        // Utility class
    }
    
    // API Base Paths
    public static final String API_V1 = "/api/v1";
    
    // Airport API Paths
    public static final String AIRPORTS = API_V1 + "/airports";
    public static final String AIRPORT_BY_CODE = "/{code}";
}