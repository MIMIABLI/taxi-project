package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ChauffeurRepository extends JpaRepository<Chauffeur,Long> {
    void deleteChauffeurById(Long Id);

}
