package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Reservation;
import com.mireille.gestiontaxiapi.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteReservationById(Long Id);
    @Query("select r from Reservation r where r.statutResa = 'VALIDEE' and r.chauffeur.id = ?1")
    List<Reservation> findAllByStatutAccepteeByChauffeur(Long id);

}
