package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteClientById(Long Id);
}
