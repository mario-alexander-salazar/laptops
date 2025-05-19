package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {

    private Product product;
    private int quantity;

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
