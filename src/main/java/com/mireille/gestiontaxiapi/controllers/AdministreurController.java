package com.mireille.gestiontaxiapi.controllers;

import com.mireille.gestiontaxiapi.models.Administrateur;
import com.mireille.gestiontaxiapi.models.Chauffeur;
import com.mireille.gestiontaxiapi.models.Role;
import com.mireille.gestiontaxiapi.models.UserType;
import com.mireille.gestiontaxiapi.services.AdministrateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministreurController {

    private AdministrateurService administrateurService;
    public AdministreurController(AdministrateurService administrateurService){
        this.administrateurService = administrateurService;
    }
    @PostMapping("/add")
    public ResponseEntity<Administrateur> addAdmin(@RequestBody Administrateur administrateur ){
        administrateur.setRole(Role.ADMIN);
        Administrateur administrateur1 = administrateurService.saveAdmin(administrateur);
        return new ResponseEntity<>(administrateur1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Administrateur>> getAllAdmin(){
        List<Administrateur> administrateurList=administrateurService.findAll();
        return new ResponseEntity<>(administrateurList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Administrateur> getAdminById(@PathVariable("id") Long id) throws Exception {
       Administrateur administrateur= administrateurService.findAdministrateurById(id);
       return new ResponseEntity<>(administrateur, HttpStatus.OK);
    }

    @GetMapping("/findbylogin/{login}")
    public ResponseEntity<Administrateur> getAdminById(@PathVariable("login") String login) throws Exception {
        Administrateur administrateur= administrateurService.findAdministrateurByLogin(login);
        return new ResponseEntity<>(administrateur, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Administrateur> updateAdmin(@RequestBody Administrateur administrateur){
        Administrateur updateAdmin=administrateurService.update(administrateur);
        return new ResponseEntity<>(administrateur,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.administrateurService.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
