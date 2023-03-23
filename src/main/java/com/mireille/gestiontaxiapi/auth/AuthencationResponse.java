package com.mireille.gestiontaxiapi.auth;

import com.mireille.gestiontaxiapi.models.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthencationResponse implements Serializable {

    private String token;
    private UserType userType;
    private String login;

}
