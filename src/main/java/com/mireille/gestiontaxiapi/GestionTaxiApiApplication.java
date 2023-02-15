package com.mireille.gestiontaxiapi;

import com.mireille.gestiontaxiapi.controllers.AdministreurController;
import com.mireille.gestiontaxiapi.models.Administrateur;
import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.models.StatutTrajet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionTaxiApiApplication {

	public static AdministreurController administreurController;
	public static void main(String[] args) {
		SpringApplication.run(GestionTaxiApiApplication.class, args);
		String statut = StatutTrajet.A_DESTINATION.name();
		System.out.println(statut);

		Administrateur administrateur = new Administrateur();
		administrateur.setLogin("roro");
		administrateur.setPassword("1234");
		administrateur.setEmail("roky@gmail.com");
		administreurController.addAdmin(administrateur);


	}



}
