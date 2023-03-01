package com.mireille.gestiontaxiapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "T_CHAUFFEUR")
public class Chauffeur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "login")
    private String login;
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
    @Column(name = "positionGPS")
    private String positionGPS;
    @OneToMany(mappedBy = "chauffeur")
    private List<Reservation> listReservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Chauffeur() {
        setChauffeurRole();
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


}
