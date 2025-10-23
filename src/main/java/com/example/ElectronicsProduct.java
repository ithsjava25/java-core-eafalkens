package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {
    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id,
                              String name,
                              Category category,
                              BigDecimal price,
                              int warrantyMonths,
                              BigDecimal weight) {

        super(id, name, category, price);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }

    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal base = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5)) > 0) {
            base = base.add(BigDecimal.valueOf(49));
        }
        return base;
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }
}