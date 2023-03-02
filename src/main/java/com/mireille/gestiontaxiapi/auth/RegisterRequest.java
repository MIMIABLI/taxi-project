package com.mireille.gestiontaxiapi.auth;

import com.mireille.gestiontaxiapi.models.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String prenom;
    private String nom;
    private String login;
    private String email;
    private String password;
    private String telephone;
    private UserType userType;

}
