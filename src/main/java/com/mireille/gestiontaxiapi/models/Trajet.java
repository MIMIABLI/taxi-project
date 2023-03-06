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
    private Long id;
    @NonNull
    @Column(name = "lieuDeDepart")
    private String lieuDeDepart;
    @NonNull
    @Column(name = "lieuDarrive")
    private String lieuDArrive;
    @Column(name = "dureeTrajet")
    private String dureeTrajet;
    @Column(name = "prix")
    private Double prix;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutTrajet statut;
    @NonNull
    @Column(name = "secteur")
    private String secteur;
    @OneToOne(mappedBy = "")
    @JoinColumn(name = "id")
    private Reservation reservation;


    public Trajet() {

    }
}
