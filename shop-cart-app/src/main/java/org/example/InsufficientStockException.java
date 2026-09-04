package org.example;

/**
 * Thrown when a {@link Cart} is asked to add more units of a {@link Product} than
 * are currently available in stock.
 */
public class InsufficientStockException extends RuntimeException {
}
