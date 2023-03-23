package com.mireille.gestiontaxiapi.services;

import com.mireille.gestiontaxiapi.models.Administrateur;
import com.mireille.gestiontaxiapi.repositories.AdministrateurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AdministrateurService {

    private AdministrateurRepository administrateurRepository;

    public AdministrateurService(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;

    }
    public Administrateur findAdministrateurById(Long Id) throws Exception {
        Optional<Administrateur> optionalAdministrateur=this.administrateurRepository.findById(Id);
        Administrateur administrateur= new Administrateur();
        if (optionalAdministrateur.isPresent()) {
            administrateur= optionalAdministrateur.get();
        }else {
            throw new Exception("id introuvable");

        }
        return administrateur;
    }

    public Administrateur findAdministrateurByLogin(String login) throws Exception {
        Optional<Administrateur> optionalAdministrateur=this.administrateurRepository.findByLogin(login);
        Administrateur administrateur= new Administrateur();
        if (optionalAdministrateur.isPresent()) {
            administrateur= optionalAdministrateur.get();
        }else {
            throw new Exception("login introuvable");

        }
        return administrateur;
    }

    public Administrateur saveAdmin(Administrateur admin){
       return this.administrateurRepository.save(admin);
    }
    public void delete(Long id){
        this.administrateurRepository.deleteById(id);
    }
    public List<Administrateur> findAll(){
        List<Administrateur> administrateurList= new ArrayList<>();
        administrateurList=this.administrateurRepository.findAll();
        return administrateurList;
    }
    public Administrateur update(Administrateur admin ){
            return this.saveAdmin(admin);


    }

    }
