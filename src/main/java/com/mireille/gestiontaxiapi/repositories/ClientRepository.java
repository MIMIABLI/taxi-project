package com.mireille.gestiontaxiapi.repositories;

import com.mireille.gestiontaxiapi.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteClientById(Long Id);
    Optional<Client> findByLogin(String login);
}
