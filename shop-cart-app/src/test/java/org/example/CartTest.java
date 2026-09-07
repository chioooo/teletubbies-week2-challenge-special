package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

        // -----------------------------------------------------------------------
        // Test 1: same product added twice → one entry with accumulated quantity
        // -----------------------------------------------------------------------
        @Test
        void addingSameProductTwiceAccumulatesQuantityInsteadOfDuplicating() {
                // Arrange
                Cart cart = new Cart();
                Product keyboard = new PhysicalProduct("SKU-001", "Keyboard", 50.0, 10);

                // Act
                cart.add(keyboard, 2);
                cart.add(keyboard, 3);

                // Assert
                assertEquals(1, cart.getItems().size(), "Cart should have only one entry for the same product");
                assertEquals(5, cart.getItems().get(keyboard), "Quantity should be accumulated to 5");
        }

        // -----------------------------------------------------------------------
        // Test 2: requesting more units than stock allows → InsufficientStockException
        // -----------------------------------------------------------------------
        @Test
        void addingMoreThanAvailableStockThrowsInsufficientStockException() {
                // Arrange
                Cart cart = new Cart();
                Product product = new PhysicalProduct("SKU-002", "Mouse", 25.0, 5);
                cart.add(product, 3); // 3 already in cart, 2 remaining

                // Act & Assert
                assertThrows(InsufficientStockException.class, () -> cart.add(product, 3),
                                "Should throw when accumulated quantity exceeds stock");
        }

        // -----------------------------------------------------------------------
        // Test 3: zero or negative quantity → InvalidQuantityException
        // -----------------------------------------------------------------------
        @Test
        void addingZeroOrNegativeQuantityThrowsInvalidQuantityException() {
                // Arrange
                Cart cart = new Cart();
                Product product = new DigitalProduct("SKU-003", "eBook", 15.0, 99);

                // Act & Assert
                assertThrows(InvalidQuantityException.class, () -> cart.add(product, 0),
                                "Zero quantity should be rejected");
                assertThrows(InvalidQuantityException.class, () -> cart.add(product, -1),
                                "Negative quantity should be rejected");
        }

        // -----------------------------------------------------------------------
        // Test 4: total differs between Physical and Digital due to tax + shipping
        // -----------------------------------------------------------------------
        @Test
        void totalAppliesTaxAndShippingDifferentlyPerProductType() {
                // Arrange — same price, same quantity, different product type
                Product physical = new PhysicalProduct("SKU-004", "Headset", 100.0, 10);
                Product digital = new DigitalProduct("SKU-005", "Audiobook", 100.0, 10);

                Cart physicalCart = new Cart();
                Cart digitalCart = new Cart();

                // Act
                physicalCart.add(physical, 1);
                digitalCart.add(digital, 1);

                // Physical: 100 + (100 * 0.16) + (2.50 * 1) = 118.50
                double expectedPhysical = 100 + (100 * 0.16) + (2.50 * 1);
                // Digital: 100 + (100 * 0.08) + 0 = 108.00
                double expectedDigital = 100 + (100 * 0.08);

                // Assert
                assertEquals(expectedPhysical, physicalCart.total(), 0.001,
                                "Physical total should include 16% tax and $2.50 shipping");
                assertEquals(expectedDigital, digitalCart.total(), 0.001,
                                "Digital total should include 8% tax and zero shipping");
                assertNotEquals(physicalCart.total(), digitalCart.total(),
                                "Totals must differ between product types");
        }
}
