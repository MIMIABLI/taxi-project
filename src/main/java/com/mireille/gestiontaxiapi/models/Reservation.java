package com.mireille.gestiontaxiapi.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "T_RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "Client_Id")
    private Client client;
    @ManyToOne()
    @JoinColumn(name = "Chauffeur_Id")
    private Chauffeur chauffeur;
    @Column(name = "date")
    private Date date;
    @Column(name = "heureDepart")
    private Date heureDepart;
    @Column(name = "heureDeArrive")
    private Date heureArrive;
    @Column(name = "statut")
    private String statut;
    @OneToOne
    @JoinColumn(name = "id_Trajet")
    private Trajet trajet;


}
