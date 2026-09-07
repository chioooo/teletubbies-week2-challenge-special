package org.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Holds products and their quantities. Adding the same product twice must
 * increase
 * its quantity — not create a second, separate entry.
 */
public class Cart {

    private final Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        int alreadyInCart = items.getOrDefault(product, 0);
        int totalRequested = alreadyInCart + quantity;
        int available = product.getStock() - alreadyInCart;

        if (totalRequested > product.getStock()) {
            throw new InsufficientStockException(product.getName(), quantity, available);
        }

        items.merge(product, quantity, Integer::sum);
    }

    public double total() {
        return items.entrySet().stream()
                .mapToDouble(entry -> {
                    Product p = entry.getKey();
                    int qty = entry.getValue();
                    double subtotal = p.getPrice() * qty;
                    double tax = subtotal * p.taxRate();
                    double shipping = p.shippingCost(qty);
                    return subtotal + tax + shipping;
                })
                .sum();
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
