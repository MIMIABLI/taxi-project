package com.mireille.gestiontaxiapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "T_CHAUFFEUR")
public class Chauffeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "photos")
    private String photos;
    @Column(name = "typeDeVehicules")
    private String typeDeVehicules;
    @Column(name = "couleurDuVehicule")
    private String couleurDuVehicule;
    @Column(name = "immatriculationDuVehicule")
    private String immatriculationDuVehicule;
    @Column(name = "note")
    private Double note;
    @Column(name = "positionGPS")
    private String positionGPS;
    @OneToMany(mappedBy = "chauffeur")
    private List<Reservation> listReservation;




}
