package org.example;

/**
 * Base type for anything that can be added to a {@link Cart}.
 */
public abstract class Product {

    /**
     * Shipping cost for buying {@code quantity} units of this product.
     * Implement per subclass — no instanceof/type checks here or at call sites.
     */
    public abstract double shippingCost(int quantity);

    /**
     * Tax rate applied to this product's price (e.g. 0.16 for 16%).
     */
    public abstract double taxRate();
}
