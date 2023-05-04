package com.mireille.gestiontaxiapi.dao.services;
import com.mireille.gestiontaxiapi.models.Reservation;
import com.mireille.gestiontaxiapi.dao.repositories.ReservationRepository;
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
        return this.reservationRepository.save(reservation);
    }

    public void delete(Long Id) {

        this.reservationRepository.deleteById(Id);
    }

    public List<Reservation> findAll() {
        List<Reservation> reservationList = new ArrayList<>();
        reservationList = this.reservationRepository.findAll();

        return reservationList;
    }

    public List<Reservation> findAllByChauffeur(String login) {
        List<Reservation> reservationList = new ArrayList<>();
        reservationList = this.reservationRepository.findAllByChauffeur(login);

        return reservationList;
    }

    public List<Reservation> findAllByClientLogin(String login) {
        List<Reservation> reservationList = new ArrayList<>();
        reservationList = this.reservationRepository.findAllByClientLogin(login);

        return reservationList;
    }

    public List<Reservation> findAllAccepteeByChauffeur(String loginChauffeur) {
        List<Reservation> reservationAccepteeList = new ArrayList<>();
        reservationAccepteeList = this.reservationRepository.findAllByStatutAccepteeByChauffeur(loginChauffeur);
        return reservationAccepteeList;
    }

    public Reservation update(Reservation reservation) {
        return this.saveReservation(reservation);

    }


}



