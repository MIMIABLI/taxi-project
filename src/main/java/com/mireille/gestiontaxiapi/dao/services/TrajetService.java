package com.mireille.gestiontaxiapi.dao.services;
import com.mireille.gestiontaxiapi.models.Trajet;
import com.mireille.gestiontaxiapi.dao.repositories.TrajetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TrajetService {

    private TrajetRepository trajetRepository;

    public TrajetService(TrajetRepository trajetRepository) {

        this.trajetRepository = trajetRepository;
    }

    public Trajet findTrajetById (Long Id) throws Exception {
        Optional<Trajet> optionalTrajet = this.trajetRepository.findById(Id);
        Trajet trajet = new Trajet();
        if(optionalTrajet.isPresent()) {
            trajet = optionalTrajet.get();

        }else {
            throw new Exception("id introuvable");
        }
        return trajet;}

    public Trajet saveTrajet(Trajet trajet) {
        this.trajetRepository.save(trajet);
        return trajet;
    }

    public void delete(Long Id) {
        this.trajetRepository.deleteById(Id);
    }

    public List<Trajet> findAll(){
        List<Trajet> trajetList=new ArrayList<>();
        trajetList=this.trajetRepository.findAll();
        return trajetList;
    }

   /* public List<Trajet> findAllByStatut(String statut){
        List<Trajet> trajetListByStatut = new ArrayList<>();
        trajetListByStatut = this.trajetRepository.findAll(statut);
        return trajetListByStatut;
    }*/

    public Trajet update(Trajet trajet) {
        return this.saveTrajet(trajet);


    }


}
