package com.mireille.gestiontaxiapi.auth;

import com.mireille.gestiontaxiapi.config.JwtService;
import com.mireille.gestiontaxiapi.models.*;
import com.mireille.gestiontaxiapi.repositories.AdministrateurRepository;
import com.mireille.gestiontaxiapi.repositories.ChauffeurRepository;
import com.mireille.gestiontaxiapi.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final AdministrateurRepository administrateurRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private String jwtToken;
    private AuthencationResponse authencationResponse = new AuthencationResponse();

    public AuthencationResponse register(RegisterRequest request) {

        var user = RegisterRequest.builder()
                .nom(request.getNom())
                .login(request.getLogin())
                .prenom(request.getPrenom())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
                .build();

        if (user.getUserType() == UserType.CLIENT) {
            Client client = saveClient(request, user);
            jwtToken = jwtService.generateToken(client, UserType.CLIENT.getUserTypeInt());
            authencationResponse.setUserType(UserType.CLIENT);
        } else if (user.getUserType() == UserType.CHAUFFEUR) {
            Chauffeur chauffeur = saveChauffeur(request, user);
            jwtToken = jwtService.generateToken(chauffeur, UserType.CHAUFFEUR.getUserTypeInt());
            authencationResponse.setUserType(UserType.CHAUFFEUR);
        } else if (user.getUserType() == UserType.ADMIN) {
            Administrateur administrateur = saveAdmin(request, user);
            jwtToken = jwtService.generateToken(administrateur);
            authencationResponse.setUserType(UserType.ADMIN);
        } else {
            System.out.println("user Type non renseigné !");
        }

        tokenRepository.save(UserToken.builder().token(jwtToken).build());

        authencationResponse.setToken(jwtToken);
        return authencationResponse;
    }

    public AuthencationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        if(clientRepository.findByLogin(request.getLogin()).isPresent()) {
          var userVar = clientRepository.findByLogin(request.getLogin()).orElseThrow();
          authencationResponse.setUserType(UserType.CLIENT);
          jwtToken = jwtService.generateToken(userVar);

        } else if (chauffeurRepository.findByLogin(request.getLogin()).isPresent()) {
            var userVar = chauffeurRepository.findByLogin(request.getLogin()).orElseThrow();
            authencationResponse.setUserType(UserType.CHAUFFEUR);
            jwtToken = jwtService.generateToken(userVar);

        } else if (administrateurRepository.findByLogin(request.getLogin()).isPresent()) {
            var userVar = administrateurRepository.findByLogin(request.getLogin()).orElseThrow();
            authencationResponse.setUserType(UserType.ADMIN);
            jwtToken = jwtService.generateToken(userVar);
        }

        authencationResponse.setToken(jwtToken);
        return authencationResponse;
    }

    private Client saveClient(RegisterRequest request, RegisterRequest user) {

        Client client = new Client();
        client.setLogin(user.getLogin());
        client.setClientUserType(UserType.CLIENT);
        client.setPassword(user.getPassword());
        client.setRole(Role.USER);
        client.setEmail(user.getEmail());
        client.setTelephone(user.getTelephone());
        client.setNom(user.getNom());
        client.setPrenom(user.getPrenom());

        clientRepository.save(client);
        return client;
    }

    private Chauffeur saveChauffeur(RegisterRequest request, RegisterRequest user) {
        Chauffeur chauffeur = Chauffeur.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .login(request.getLogin())
                .password(user.getPassword())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .role(Role.USER)
                .build();

        chauffeurRepository.save(chauffeur);
        return chauffeur;
    }

    private Administrateur saveAdmin(RegisterRequest request, RegisterRequest user) {
        Administrateur administrateur = Administrateur.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .login(request.getLogin())
                .password(user.getPassword())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .build();

        administrateurRepository.save(administrateur);
        return administrateur;
    }
}
