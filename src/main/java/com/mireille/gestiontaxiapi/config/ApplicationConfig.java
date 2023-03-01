package com.mireille.gestiontaxiapi.config;

import com.mireille.gestiontaxiapi.repositories.AdministrateurRepository;
import com.mireille.gestiontaxiapi.repositories.ChauffeurRepository;
import com.mireille.gestiontaxiapi.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ClientRepository clientRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final AdministrateurRepository administrateurRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if(clientRepository.findByLogin(username).isPresent()) {
            clientRepository.findByLogin(username);
        } else if (chauffeurRepository.findByLogin(username).isPresent()) {
                chauffeurRepository.findByLogin(username);
        } else if (administrateurRepository.find)
        }
    }
}
