package com.mireille.gestiontaxiapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chauffeur {
    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String email;

    @Id
    @GeneratedValue
    private Long id;
}
