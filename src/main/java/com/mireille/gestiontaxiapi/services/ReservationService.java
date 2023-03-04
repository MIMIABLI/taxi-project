package com.mireille.gestiontaxiapi.services;
import com.mireille.gestiontaxiapi.models.Reservation;
import com.mireille.gestiontaxiapi.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation findReservationById(Long Id) throws Exception {
        Optional<Reservation> optionalReservation = this.reservationRepository.findById(Id);
        Reservation reservation = new Reservation();

        if (optionalReservation.isPresent()) {
            reservation = optionalReservation.get();

        } else {
            throw new Exception("id introuvable");
        }
        return reservation;
    }

    public Reservation saveReservation(Reservation reservation) {
        this.reservationRepository.save(reservation);
        return reservation;
    }

    public void delete(Long Id) {

        this.reservationRepository.deleteById(Id);
    }

    public List<Reservation> findAll() {
        List<Reservation> reservationList = new ArrayList<>();
        reservationList = this.reservationRepository.findAll();

        return reservationList;
    }

    public List<Reservation> findAllAccepteeByChauffeur(Long idChauffeur) {
        List<Reservation> reservationAccepteeList = new ArrayList<>();
        reservationAccepteeList = this.reservationRepository.findAllByStatutAccepteeByChauffeur(idChauffeur);
        return reservationAccepteeList;
    }

    public Reservation update(Reservation reservation) {
        return this.saveReservation(reservation);

    }


}



