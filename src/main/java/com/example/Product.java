package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(UUID id, String name, Category category, BigDecimal price) {
      this.id = id;
      this.name = name;
      this.category = category;
      this.price = price;
    }

    public UUID uuid() {
        return id;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void price(BigDecimal price) {
        this.price = price;
    }
    public abstract String productDetails();
}