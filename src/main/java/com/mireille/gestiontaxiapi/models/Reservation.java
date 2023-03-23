package com.mireille.gestiontaxiapi.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "T_RESERVATION")
@JsonIdentityInfo(
        scope = Reservation.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idReservation")
@JsonSerialize
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    @JsonProperty("idReservation")
    private Long id;

    @JsonView
    @ManyToOne
    @JoinColumn(name = "Client_login", referencedColumnName = "login")
    private Client client;

    @JsonView
    @ManyToOne
    @JoinColumn(name = "Chauffeur_login", referencedColumnName = "login")
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
    @JsonManagedReference
    private Trajet trajet;

    @JsonCreator
    public Reservation() {
    }
}
