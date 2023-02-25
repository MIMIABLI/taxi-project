package com.mireille.gestiontaxiapi.services;

import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findClientById(Long Id) throws Exception {
        Optional<Client> optionalClient = this.clientRepository.findById(Id);
        Client client = new Client();

        if (optionalClient.isPresent()) {
            client = optionalClient.get();

        } else {
            throw new Exception("id introuvable");
        }
        return client;
    }

    public Client findClientByLogin(String clientLogin) throws Exception{
        Optional<Client> optionalClient = this.clientRepository.findClientByLogin(clientLogin);
        Client client = new Client();

        if (optionalClient.isPresent()) {
            client = optionalClient.get();

        } else {
            throw new Exception("login introuvable");
        }
        return client;
    }

    public Client saveClient(Client client) {
        this.clientRepository.save(client);
        return client;
    }

    public void delete(Long Id) {

        this.clientRepository.deleteById(Id);
    }

    public List<Client> findAll() {
        List<Client> clientList = (List<Client>) this.clientRepository.findAll();
        return clientList;
    }

    public Client update(Client client) {
        return this.saveClient(client);

    }


}


