package com.mireille.gestiontaxiapi.controllers;

import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.models.Role;
import com.mireille.gestiontaxiapi.models.UserType;
import com.mireille.gestiontaxiapi.services.ClientService;
import com.mireille.gestiontaxiapi.services.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/client")
    public class ClientController {

        private ClientService clientService;
        public ClientController(ClientService clientService, TrajetService trajetService){
            this.clientService = clientService;
        }

        @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Client> addClient(@RequestBody Client client ){
            client.setUserType(UserType.CLIENT);
            client.setRole(Role.USER);
            Client newClient = clientService.saveClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Client>> getAllClient(){
            List<Client> clientList=clientService.findAll();
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
        @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws Exception {
            Client client= clientService.findClientById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }

        @GetMapping(value = "/findByLogin/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Client> getClientById(@PathVariable("login") String login) throws Exception {
            Client client = clientService.findClientByLogin(login);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }

        @PutMapping(value ="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Client> updateClient(@RequestBody Client client){
            Client updateClient=clientService.updateClient(client);
            return new ResponseEntity<>(updateClient,HttpStatus.OK);
        }
        @DeleteMapping(value ="/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            clientService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
