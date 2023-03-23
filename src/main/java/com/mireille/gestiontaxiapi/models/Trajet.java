package com.mireille.gestiontaxiapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_TRAJET")
@JsonIdentityInfo(
        scope = Trajet.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idTrajet")
@JsonSerialize
public class Trajet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trajet_id")
    @JsonProperty("idTrajet")
    private Long id;

    @Column(name = "lieuDeDepart")
    private String lieuDeDepart;

    @Column(name = "lieuDarrive")
    private String lieuDArrive;

    @Column(name = "dureeTrajet")
    private String dureeTrajet;

    @Column(name = "prix")
    private Double prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutTrajet statut;

    @Column(name = "secteur")
    private String secteur;

    @OneToOne(mappedBy = "trajet")
    @JoinColumn(name = "trajet_id", referencedColumnName = "reservation_id")
    @JsonBackReference
    private Reservation reservation;


    public Trajet() {

    }
}
