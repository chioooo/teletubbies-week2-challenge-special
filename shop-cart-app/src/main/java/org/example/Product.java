package org.example;

import java.util.Objects;

/**
 * Base type for anything that can be added to a {@link Cart}.
 */
public abstract class Product {

    private final String sku;
    private final String name;
    private final double price;
    private final int stock;

    protected Product(String sku, String name, double price, int stock) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Shipping cost for buying {@code quantity} units of this product.
     * Implemented per subclass — no instanceof/type checks here or at call sites.
     */
    public abstract double shippingCost(int quantity);

    /**
     * Tax rate applied to this product's price (e.g. 0.16 for 16%).
     */
    public abstract double taxRate();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product other))
            return false;
        return Objects.equals(sku, other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return name + " [" + sku + "] $" + price;
    }
}
