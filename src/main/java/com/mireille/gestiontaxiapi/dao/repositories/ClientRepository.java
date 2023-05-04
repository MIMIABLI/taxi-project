package com.mireille.gestiontaxiapi.dao.repositories;

import com.mireille.gestiontaxiapi.models.Client;
import com.mireille.gestiontaxiapi.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteClientById(Long Id);
    Optional<Client> findByLogin(String login);
    @Query("UPDATE Client c SET c.email = ?1, c.telephone = ?2 WHERE c.id = ?3")
    Client updateClient(String mail, String tel, Long id);
}
