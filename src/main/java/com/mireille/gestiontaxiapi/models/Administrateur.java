package com.mireille.gestiontaxiapi.models;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "T_ADMIN")
public class Administrateur {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private  String login;
    @Column(name = "password")
    private  String password;
    @Column(name = "email")
    private String email;


    public Administrateur(Long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;

    }

    public Administrateur() {
    }
}
