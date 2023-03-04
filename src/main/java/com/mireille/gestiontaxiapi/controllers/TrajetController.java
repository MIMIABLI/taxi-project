package com.mireille.gestiontaxiapi.controllers;


import com.mireille.gestiontaxiapi.models.Trajet;
import com.mireille.gestiontaxiapi.services.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @Controller
    @RequestMapping("/trajet")

    public class TrajetController {
        private TrajetService trajetService;
        public TrajetController(TrajetService trajetService){
            this.trajetService = trajetService;
        }
        @PostMapping("/add")
        public ResponseEntity<Trajet> addTrajet(@RequestBody Trajet trajet) {
            Trajet newTrajet = trajetService.saveTrajet(trajet);
            return new ResponseEntity<>(newTrajet, HttpStatus.CREATED);
        }
        @GetMapping("/all")
        public ResponseEntity<List<Trajet>> getAllTrajet(){
            List<Trajet> trajetList=trajetService.findAll();
            return new ResponseEntity<>(trajetList, HttpStatus.OK);
        }

        @GetMapping("/find/{id}")
        public ResponseEntity<Trajet> getTrajetById(@PathVariable("id") Long id) throws Exception {
            Trajet trajet=trajetService.findTrajetById(id);
            return new ResponseEntity<>(trajet, HttpStatus.OK);
        }
        @PutMapping("/update")
        public ResponseEntity<Trajet> updateTrajet(@RequestBody Trajet trajet){
            Trajet updateTrajet=trajetService.update(trajet);
            return new ResponseEntity<>(trajet,HttpStatus.OK);
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            trajetService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
