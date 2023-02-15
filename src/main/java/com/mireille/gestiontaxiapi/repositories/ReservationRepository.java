package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteReservationById(Long Id);

}
