package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private int idCategory;
    private String name;
    private int quantity;
    private double price;
    private byte[] picture;

}
