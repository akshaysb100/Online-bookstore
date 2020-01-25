package com.onlinebookstore.model;

import com.onlinebookstore.service.ShippingChargeProvider;

public enum CountryWiseShippingCharges implements ShippingChargeProvider {
    INDIA(50.0), OTHER_COUNTRY(200.0);

    private double shippingCharge;

    CountryWiseShippingCharges(double charges) {
        this.shippingCharge = charges;
    }

    @Override
    public Double getShippingCharge() {
        return this.shippingCharge;
    }
}
