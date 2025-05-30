package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    private int id;
    private String category;
    private String name;
    private int quantity;
    private double price;
}
