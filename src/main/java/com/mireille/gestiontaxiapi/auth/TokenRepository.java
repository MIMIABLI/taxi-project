package com.mireille.gestiontaxiapi.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findUserTokenByToken(String token);

}
