package com.mireille.gestiontaxiapi.controllers;


import com.mireille.gestiontaxiapi.models.Chauffeur;
import com.mireille.gestiontaxiapi.services.ChauffeurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/chauffeur")
public class ChauffeurController {
        private ChauffeurService chauffeurService;
        public ChauffeurController(ChauffeurService chauffeurService){
            this.chauffeurService = chauffeurService;
        }
        @PostMapping("/add")
        public ResponseEntity<Chauffeur> addChauffeur(@RequestBody Chauffeur chauffeur ){
            Chauffeur chauffeur1 = chauffeurService.saveChauffeur(chauffeur);
            return new ResponseEntity<>(chauffeur1, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Chauffeur>> getAllChauffeur(){
            List<Chauffeur> chauffeurList=chauffeurService.findAll();
            return new ResponseEntity<>(chauffeurList, HttpStatus.OK);
        }

        @GetMapping("/find/{id}")
        public ResponseEntity<Chauffeur> getChauffeurById(@PathVariable("id") Long id) throws Exception {
            Chauffeur chauffeur= chauffeurService.findChauffeurById(id);
            return new ResponseEntity<>(chauffeur, HttpStatus.OK);

        }

    @GetMapping("/findbylogin/{login}")
    public ResponseEntity<Chauffeur> getChauffeurByLogin(@PathVariable("login") String login) throws Exception {
        Chauffeur chauffeur= chauffeurService.findChauffeurByLogin(login);
        return new ResponseEntity<>(chauffeur, HttpStatus.OK);

    }

        @PutMapping("/update")
        public ResponseEntity<Chauffeur> updateChauffeur(@RequestBody Chauffeur chauffeur){
            Chauffeur newChauffeur=chauffeurService.update(chauffeur);
            return new ResponseEntity<>(newChauffeur,HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            chauffeurService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}
