package org.example;

/**
 * A product delivered digitally: no shipping cost, different tax rate than
 * {@link PhysicalProduct}.
 */
public class DigitalProduct extends Product {

    private static final double TAX_RATE = 0.08;

    public DigitalProduct(String sku, String name, double price, int stock) {
        super(sku, name, price, stock);
    }

    /** Digital products have no shipping cost. */
    @Override
    public double shippingCost(int quantity) {
        return 0;
    }

    /** Reduced tax rate: 8% (vs 16% for physical products). */
    @Override
    public double taxRate() {
        return TAX_RATE;
    }
}

