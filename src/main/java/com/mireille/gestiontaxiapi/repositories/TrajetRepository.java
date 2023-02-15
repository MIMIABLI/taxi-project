package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    void deleteTrajetById(Long Id);
}
