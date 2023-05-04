package com.mireille.gestiontaxiapi.dao.controllers;

import com.mireille.gestiontaxiapi.dao.services.ReservationService;
import com.mireille.gestiontaxiapi.models.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Controller
    @RequestMapping("/reservation")

    public class ReservationController {
        private ReservationService reservationService;
        public ReservationController(ReservationService reservationService){
            this.reservationService = reservationService;
        }

        @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation ){
            Reservation newReservation = reservationService.saveReservation(reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Reservation>> getAllReservation(){
            List<Reservation> reservationList=reservationService.findAll();
            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        }

        @GetMapping(value = "/allbychauffeur/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Reservation>> getAllReservationByChauffeur(@PathVariable("login") String login){
            List<Reservation> reservationList=reservationService.findAllByChauffeur(login);
            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        }

        @GetMapping(value = "/allbyclient/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Reservation>> getAllReservationByClient(@PathVariable("login") String login){
            List<Reservation> reservationList=reservationService.findAllByClientLogin(login);
            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        }

        @GetMapping(value = "/allacceptedbychauffeur/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Reservation>> getAllReservationAcceptee(@PathVariable("login") String login){
            List<Reservation> reservationAccepteeList = reservationService.findAllAccepteeByChauffeur(login);
            return new ResponseEntity<>(reservationAccepteeList, HttpStatus.OK);
        }

        @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) throws Exception {
            Reservation reservation=reservationService.findReservationById(id);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }

        @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
            Reservation updateReservation= reservationService.update(reservation);
            return new ResponseEntity<>(updateReservation,HttpStatus.OK);
        }

        @DeleteMapping(value = "/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            reservationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
