package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private int id;
    private String profile;
    private String maritalStatus;
    private String dni;
    private String name;
    private String email;

}
