package com.mireille.gestiontaxiapi.models;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "T_CHAUFFEUR",
        uniqueConstraints = @UniqueConstraint(columnNames = "login"))
/*@JsonIdentityInfo(
        scope = Chauffeur.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idChauffeur")*/
@JsonSerialize
public class Chauffeur implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idChauffeur")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "photos")
    private String photos;

    @Column(name = "typeDeVehicules")
    private String typeDeVehicules;

    @Column(name = "couleurDuVehicule")
    private String couleurDuVehicule;

    @Column(name = "immatriculationDuVehicule")
    private String immatriculationDuVehicule;

    @Column(name = "note")
    private Double note;

    @Column(name = "secteur")
    private String secteur;

    @JsonIgnore
    @OneToMany(mappedBy = "chauffeur",
            orphanRemoval = true)
    private List<Reservation> listReservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Chauffeur(Role role, UserType userType) {
        this.role = role;
        this.userType = userType;
    }

    @JsonCreator
    public Chauffeur() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private Role setChauffeurRole(){
        return this.role = Role.USER;
    }

    private UserType setChauffeurUserType() {
        return this.userType = UserType.CLIENT;
    }

    public UserType getUserType() {
        return userType;
    }

}
