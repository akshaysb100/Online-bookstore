package com.onlinebookstore.service;

public enum CountryWiseShippingCharges implements ShippingChargeProvider {
    INDIA(50.0), OTHER_COUNTRY(200.0);

    private double shippingCharges;

    CountryWiseShippingCharges(double charges) {
        this.shippingCharges = charges;
    }

    @Override
    public Double getShippingCharge() {
        return this.shippingCharges;
    }
}
