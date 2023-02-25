package com.mireille.gestiontaxiapi.controllers;

import com.mireille.gestiontaxiapi.models.Reservation;
import com.mireille.gestiontaxiapi.services.ReservationService;
import org.springframework.http.HttpStatus;
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

        @PostMapping("/add")
        public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation ){
            Reservation newReservation = reservationService.saveReservation(reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Reservation>> getAllReservation(){
            List<Reservation> reservationList=reservationService.findAll();
            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        }

        @GetMapping("/find/{id}")
        public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) throws Exception {
            Reservation reservation=reservationService.findReservationById(id);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }

        @PutMapping("/update")
        public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
            Reservation updateReservation= reservationService.update(reservation);
            return new ResponseEntity<>(updateReservation,HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            reservationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
