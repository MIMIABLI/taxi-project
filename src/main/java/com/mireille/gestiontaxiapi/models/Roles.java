package com.mireille.gestiontaxiapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Roles {
    @Id
    @GeneratedValue
    private Long id;
    private String roleName;
    private String desc;

}
