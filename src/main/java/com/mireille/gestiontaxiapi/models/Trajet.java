package com.mireille.gestiontaxiapi.models;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NonNull;

@Data
@Entity
@Table(name = "T_TRAJET")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trajet_id")
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

    @OneToOne(mappedBy = "trajet", cascade = CascadeType.ALL)
    @JoinColumn(name = "trajet_id", referencedColumnName = "reservation_id")
    private Reservation reservation;


    public Trajet() {

    }
}
