package com.mireille.gestiontaxiapi.services;

import com.mireille.gestiontaxiapi.models.Chauffeur;
import com.mireille.gestiontaxiapi.repositories.ChauffeurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ChauffeurService {
    private ChauffeurRepository chauffeurRepository;

    public ChauffeurService(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }
    public Chauffeur findChauffeurById(Long Id) throws Exception {
        Optional<Chauffeur> optionalChauffeur=this.chauffeurRepository.findById(Id);
         Chauffeur chauffeur= new Chauffeur();
        if (optionalChauffeur.isPresent()) {
            chauffeur= optionalChauffeur.get();
        }else {
            throw new Exception("id introuvable");

        }
        return chauffeur;
    }

    public Chauffeur findChauffeurByLogin(String login) throws Exception {
        Optional<Chauffeur> optionalChauffeur=this.chauffeurRepository.findByLogin(login);
        Chauffeur chauffeur= new Chauffeur();
        if (optionalChauffeur.isPresent()) {
            chauffeur= optionalChauffeur.get();
        }else {
            throw new Exception("login introuvable");
        }
        return chauffeur;
    }

    public Chauffeur saveChauffeur(Chauffeur user){
        this.chauffeurRepository.save(user);
        return user;
    }

    public void delete(Long Id){

        this.chauffeurRepository.deleteById(Id);
    }

    public List<Chauffeur> findAll(){
        List<Chauffeur> chauffeursList= new ArrayList<>();
        chauffeursList=this.chauffeurRepository.findAll();
        return chauffeursList;

    }
    public Chauffeur update(Chauffeur user ){
        return this.saveChauffeur(user);

    }
}
