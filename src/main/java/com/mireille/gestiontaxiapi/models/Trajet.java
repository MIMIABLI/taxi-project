package com.mireille.gestiontaxiapi.models;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "T_TRAJET")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lieuDeDepart")
    private String lieuDeDepart;
    @Column(name = "lieuDarrive")
    private String lieuDArrive;
    @Column(name = "dureeTrajet")
    private String dureeTrajet;
    @Column(name = "prix")
    private Double prix;
    @Column(name = "statut")
    private Enum statut;
    @OneToOne
    @JoinColumn(name = "id_Reservation")
    private Reservation reservation;


}
