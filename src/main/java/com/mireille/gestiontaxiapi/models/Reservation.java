package com.mireille.gestiontaxiapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Date;
@Data
@Entity
@Table(name = "T_RESERVATION")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Client_login", referencedColumnName = "login")
    @JsonManagedReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Chauffeur_login", referencedColumnName = "login")
    @JsonManagedReference
    private Chauffeur chauffeur;

    @Column(name = "date")
    private String date;

    @Column(name = "heureDepart")
    private String heureDepart;

    @Column(name = "heureDeArrive")
    private String heureArrive;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutResa statutResa;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "trajet_id", referencedColumnName = "trajet_id")
    private Trajet trajet;


}
