package org.example;

/**
 * Thrown when a {@link Cart} is asked to add more units of a {@link Product} than
 * are currently available in stock.
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String productName, int requested, int available) {
        super("Not enough stock for '" + productName + "': requested " + requested
                + " but only " + available + " available.");
    }
}
