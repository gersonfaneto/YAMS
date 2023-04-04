package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;

public class Cleaning extends Service {
    private static final double BASE_PRICE = 70.00;

    public Cleaning(String serviceType, String serviceDescription) {
        super(serviceType, serviceDescription, BASE_PRICE);
    }
}
