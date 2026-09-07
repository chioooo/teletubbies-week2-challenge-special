package org.example;

/** A product that ships physically (has a shipping cost, standard tax rate). */
public class PhysicalProduct extends Product {

    private static final double SHIPPING_PER_UNIT = 2.50;
    private static final double TAX_RATE = 0.16;

    public PhysicalProduct(String sku, String name, double price, int stock) {
        super(sku, name, price, stock);
    }

    /** $2.50 per unit ordered. */
    @Override
    public double shippingCost(int quantity) {
        return SHIPPING_PER_UNIT * quantity;
    }

    /** Standard tax rate: 16%. */
    @Override
    public double taxRate() {
        return TAX_RATE;
    }
}

