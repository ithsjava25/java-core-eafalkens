package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static final Map<String, Warehouse> INSTANCES = new HashMap<>();
    private final Map<UUID, Product> products = new HashMap<>();
    private final Set<Product> changedProducts = new HashSet<>();

    private Warehouse(String name) {
    }

    public static Warehouse getInstance(String name) {
        return INSTANCES.computeIfAbsent(name, Warehouse::new);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (products.containsKey(product.uuid())) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.put(product.uuid(), product);
    }

    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        product.price(newPrice);
        changedProducts.add(product);
    }

    public List<FoodProduct> expiredProducts() {
        List<FoodProduct> expired = new ArrayList<>();
        for (Product p : products.values()) {
            if (p instanceof FoodProduct foodProduct && foodProduct.isExpired()) {
                expired.add(foodProduct);
            }
        }
        return Collections.unmodifiableList(expired);
    }

    public List<Shippable> shippableProducts() {
        List<Shippable> out = new ArrayList<>();
        for (Product p : products.values()) {
            if (p instanceof Shippable s) {
                out.add(s);
            }
        }
        return Collections.unmodifiableList(out);
    }

    public void remove(UUID id) {
        Product removedProduct = products.remove(id);
        if (removedProduct != null) {
            changedProducts.remove(removedProduct);
        }
    }

    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public static Warehouse getInstance() {
        return getInstance("Default");
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values()
                        .stream()
                        .collect(Collectors.groupingBy(Product :: category));
    }

}