package com.mireille.gestiontaxiapi.models;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Date;
@Data
@Entity
@Table(name = "T_RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "Client_login", referencedColumnName = "login")
    private Client client;

    @ManyToOne()
    @JoinColumn(name = "Chauffeur_login", referencedColumnName = "login")
    private Chauffeur chauffeur;

    @Column(name = "date")
    private Date date;

    @Column(name = "heureDepart")
    private Date heureDepart;

    @Column(name = "heureDeArrive")
    private Date heureArrive;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutResa statutResa;

    @OneToOne
    @JoinColumn(name = "trajet_id", referencedColumnName = "trajet_id")
    private Trajet trajet;


}
