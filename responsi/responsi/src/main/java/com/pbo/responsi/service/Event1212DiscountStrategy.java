package com.pbo.responsi.service;

public class Event1212DiscountStrategy
        implements DiscountStrategy {

    @Override
    public double calculateDiscount(double totalAmount) {

        return totalAmount * 0.12;
    }

    @Override
    public String getDiscountName() {

        return "Event 12.12 - 12%";
    }
}