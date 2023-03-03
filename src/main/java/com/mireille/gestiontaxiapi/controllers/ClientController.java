package com.mireille.gestiontaxiapi.controllers;


import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.services.ClientService;
import com.mireille.gestiontaxiapi.services.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Controller
    @RequestMapping("/client")
    public class ClientController {

        private ClientService clientService;
        public ClientController(ClientService clientService, TrajetService trajetService){
            this.clientService = clientService;
        }

        @PostMapping("/add")
        public ResponseEntity<Client> addClient(@RequestBody Client client ){
            Client newClient = clientService.saveClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Client>> getAllClient(){
            List<Client> clientList=clientService.findAll();
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
        @GetMapping("/find/{id}")
        public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws Exception {
            Client client= clientService.findClientById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }

        @GetMapping("/findByLogin/{login}")
        public ResponseEntity<Client> getClientById(@PathVariable("login") String login) throws Exception {
            Client client = clientService.findClientByLogin(login);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }

        @PutMapping("/update")
        public ResponseEntity<Client> updateClient(@RequestBody Client client){
            Client updateClient=clientService.update(client);
            return new ResponseEntity<>(client,HttpStatus.OK);
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            clientService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
