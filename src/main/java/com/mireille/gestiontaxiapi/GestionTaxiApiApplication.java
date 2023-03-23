package com.mireille.gestiontaxiapi;

import com.mireille.gestiontaxiapi.controllers.AdministreurController;
import com.mireille.gestiontaxiapi.models.Administrateur;
import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.models.StatutTrajet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionTaxiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionTaxiApiApplication.class, args);
	}



}
