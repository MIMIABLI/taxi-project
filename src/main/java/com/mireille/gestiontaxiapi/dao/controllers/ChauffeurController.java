package com.mireille.gestiontaxiapi.dao.controllers;


import com.mireille.gestiontaxiapi.dao.services.ChauffeurService;
import com.mireille.gestiontaxiapi.models.Chauffeur;
import com.mireille.gestiontaxiapi.models.Role;
import com.mireille.gestiontaxiapi.models.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/chauffeur")
public class ChauffeurController {
        private ChauffeurService chauffeurService;
        private PasswordEncoder passwordEncoder;
        private String mdpChauffeurParDefaut;

        public ChauffeurController(ChauffeurService chauffeurService){
            this.chauffeurService = chauffeurService;
        }

        @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Chauffeur> addChauffeur(@RequestBody Chauffeur chauffeur ){
            chauffeur.setUserType(UserType.CHAUFFEUR);
            chauffeur.setRole(Role.USER);
            mdpChauffeurParDefaut = chauffeur.getNom().toUpperCase() + chauffeur.getPrenom().charAt(0);
            System.out.println("mdp par défaut: " + mdpChauffeurParDefaut);
            chauffeur.setPassword(passwordEncoder.encode(mdpChauffeurParDefaut));
            Chauffeur newChauffeur = chauffeurService.saveChauffeur(chauffeur);
            return new ResponseEntity<>(newChauffeur, HttpStatus.CREATED);
        }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Chauffeur>> getAllChauffeur(){
            List<Chauffeur> chauffeurList=chauffeurService.findAll();
            return new ResponseEntity<>(chauffeurList, HttpStatus.OK);
        }

        @GetMapping(value = "/allbysecteur/{secteur}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Chauffeur>> getAllChauffeurBySecteur(@PathVariable("secteur") String secteur){
            List<Chauffeur> chauffeurListBySecteur = chauffeurService.findAllBySecteur(secteur);
            return new ResponseEntity<>(chauffeurListBySecteur, HttpStatus.OK);
        }

        @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Chauffeur> getChauffeurById(@PathVariable("id") Long id) throws Exception {
            Chauffeur chauffeur= chauffeurService.findChauffeurById(id);
            return new ResponseEntity<>(chauffeur, HttpStatus.OK);

        }

    @GetMapping(value = "/findbylogin/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chauffeur> getChauffeurByLogin(@PathVariable("login") String login) throws Exception {
        Chauffeur chauffeur= chauffeurService.findChauffeurByLogin(login);
        return new ResponseEntity<>(chauffeur, HttpStatus.OK);

    }

        @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Chauffeur> updateChauffeur(@RequestBody Chauffeur chauffeur){
            Chauffeur newChauffeur=chauffeurService.update(chauffeur);
            return new ResponseEntity<>(newChauffeur,HttpStatus.OK);
        }

        @DeleteMapping(value = "/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            chauffeurService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}
