package com.mireille.gestiontaxiapi.models;

public enum StatutTrajet {
    EN_ATTENTE("en attente du chauffeur"),
    ENCOURS("en cours"),
    ANNULER("annuler la reservation"),
    A_DESTINATION("vous etes arrive");


    public String etat;

    StatutTrajet(String etat) {
        this.etat = etat;
    }
}
