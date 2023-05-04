package com.mireille.gestiontaxiapi.dao.repositories;

import com.mireille.gestiontaxiapi.models.Reservation;
import com.mireille.gestiontaxiapi.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteReservationById(Long Id);

    @Query("SELECT r FROM Reservation r WHERE r.statutResa = 'EN_ATTENTE' and r.chauffeur.login = ?1")
    List<Reservation> findAllByChauffeur(String login);

    @Query("select r from Reservation r where r.statutResa = 'VALIDEE' and r.chauffeur.login = ?1")
    List<Reservation> findAllByStatutAccepteeByChauffeur(String login);

    @Query("SELECT r FROM Reservation r WHERE r.client.login = ?1")
    List<Reservation> findAllByClientLogin(String login);

}
