package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class SSD extends Component {
    private static final String COMPONENT_TYPE = "SSD";
    private static final double COMPONENT_PRICE = 20.00;

    public SSD(String componentDescription, double componentCost) {
        super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
    }
}
