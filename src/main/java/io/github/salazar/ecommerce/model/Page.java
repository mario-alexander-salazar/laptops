package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private int id;
    private String description;
    private String path;
}
