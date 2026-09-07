package org.example;

/**
 * Thrown when a {@link Cart} is asked to add a non-positive quantity of a
 * {@link Product}.
 */
public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(int quantity) {
        super("Quantity must be greater than zero, but got: " + quantity);
    }
}
