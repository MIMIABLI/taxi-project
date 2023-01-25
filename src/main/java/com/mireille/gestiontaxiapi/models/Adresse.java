package com.mireille.gestiontaxiapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Embedded  TO DO
public class Adresse {
    private Long id;
    private String nom;
    private String prenom;
    private Integer numero;
    private String code;
}
