package com.mireille.gestiontaxiapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Administrateur {
    @Id
    @GeneratedValue
    private Long id;
    private  Integer login;
    private  String password;
    private String email;
}
