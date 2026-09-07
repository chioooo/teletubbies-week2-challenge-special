package org.example;

/** Manual smoke test: build a couple of products, add them to a Cart, print the total. */
public class Main {

    public static void main(String[] args) {

        // --- Products ---
        Product keyboard  = new PhysicalProduct("SKU-001", "Mechanical Keyboard", 75.00, 10);
        Product mouse     = new PhysicalProduct("SKU-002", "Wireless Mouse",       35.00,  5);
        Product antivirus = new DigitalProduct ("SKU-003", "Antivirus License",    29.99, 99);

        Cart cart = new Cart();

        // --- Normal flow ---
        try {
            cart.add(keyboard, 2);   // 2 units
            cart.add(keyboard, 1);   // same SKU → accumulates to 3
            cart.add(mouse, 2);
            cart.add(antivirus, 1);

            System.out.println("=== Cart Items ===");
            cart.getItems().forEach((product, qty) ->
                System.out.printf("  %-25s x%d  (tax=%.0f%%  shipping=$%.2f/unit)%n",
                        product.getName(),
                        qty,
                        product.taxRate() * 100,
                        product.shippingCost(1)));

            System.out.printf("%nTotal: $%.2f%n", cart.total());

        } catch (InvalidQuantityException | InsufficientStockException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }

        // --- Demo: InsufficientStockException ---
        System.out.println("\n=== Demo: stock exceeded ===");
        try {
            cart.add(mouse, 10); // mouse only has 5 stock, 2 already in cart
        } catch (InsufficientStockException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        // --- Demo: InvalidQuantityException ---
        System.out.println("\n=== Demo: invalid quantity ===");
        try {
            cart.add(keyboard, -3);
        } catch (InvalidQuantityException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }
    }
}

