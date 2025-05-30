package io.github.salazar.ecommerce.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Audit {
    private int id;
    private String nameTable;
    private String operation;
    private String previousValue;
    private String newValue;
    private LocalDateTime date;
    private String user;
    private String scheme;
    private Boolean activate;
    private Boolean trigger;
}
