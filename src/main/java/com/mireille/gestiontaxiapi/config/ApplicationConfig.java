package com.mireille.gestiontaxiapi.config;

import com.mireille.gestiontaxiapi.repositories.AdministrateurRepository;
import com.mireille.gestiontaxiapi.repositories.ChauffeurRepository;
import com.mireille.gestiontaxiapi.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
          return clientRepository.findByLogin(username).orElseThrow();
        } else if (chauffeurRepository.findByLogin(username).isPresent()) {
            return chauffeurRepository.findByLogin(username).orElseThrow();
        } else if (administrateurRepository.findByLogin(username).isPresent()) {
             return administrateurRepository.findByLogin(username).orElseThrow();
            } else {
                throw  new UsernameNotFoundException(username + " not found.");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
