package com.mireille.gestiontaxiapi.models;

public enum UserType {

    CLIENT(1),
    CHAUFFEUR(2),
    ADMIN(3);

    private Integer userTypeInt;

    UserType(Integer userTypeInt) {
        this.userTypeInt = userTypeInt;
    }

    public Integer getUserTypeInt() {
        return userTypeInt;
    }
}
