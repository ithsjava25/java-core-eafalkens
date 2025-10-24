package com.example;

import java.util.HashMap;
import java.util.Map;

public final class Category {
    private static final Map<String, Category> CACHE = new HashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String trimmed = name.trim();

        String normalized = trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();

       return CACHE.computeIfAbsent(normalized, Category::new);
    }

    public String name() {
        return name;
    }

    public String getName() {
        return name;
    }
}