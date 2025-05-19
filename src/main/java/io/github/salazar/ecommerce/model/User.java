package io.github.salazar.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private int idProfile;
    private int idMaritalStatus;
    private String dni;
    private String name;
    private String email;
    private String password;

}
