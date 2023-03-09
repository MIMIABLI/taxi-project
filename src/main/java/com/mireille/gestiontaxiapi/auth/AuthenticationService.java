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


             Client client = saveClient(request, user);
            jwtToken = jwtService.generateToken(client);
            authencationResponse.setUserType(UserType.CLIENT);

        tokenRepository.save(UserToken.builder().token(jwtToken).build());
        authencationResponse.setLogin(client.getLogin());
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


        tokenRepository.save(UserToken.builder().token(jwtToken).build());
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

    private Chauffeur saveChauffeur(Chauffeur chauffeurRequest) {
        String mdpParDefaut = chauffeurRequest.getNom().toUpperCase() + (chauffeurRequest.getPrenom().toLowerCase().charAt(0));
        Chauffeur chauffeur = Chauffeur.builder()
                .nom(chauffeurRequest.getNom())
                .login(chauffeurRequest.getLogin())
                .prenom(chauffeurRequest.getPrenom())
                .password(passwordEncoder.encode(mdpParDefaut))
                .email(chauffeurRequest.getEmail())
                .telephone(chauffeurRequest.getTelephone())
                .userType(UserType.CHAUFFEUR)
                .role(Role.USER)
                .couleurDuVehicule(chauffeurRequest.getCouleurDuVehicule())
                .secteur(chauffeurRequest.getSecteur())
                .immatriculationDuVehicule(chauffeurRequest.getImmatriculationDuVehicule())
                .typeDeVehicules(chauffeurRequest.getTypeDeVehicules())
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

    public AuthencationResponse registerAdmin(RegisterRequest request) {

        var user = RegisterRequest.builder()
                .nom(request.getNom())
                .login(request.getLogin())
                .prenom(request.getPrenom())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.ADMIN)
                .build();

        Administrateur administrateur = saveAdmin(request, user);
        jwtToken = jwtService.generateToken(administrateur);
        authencationResponse.setUserType(UserType.ADMIN);

        tokenRepository.save(UserToken.builder().token(jwtToken).build());

        authencationResponse.setToken(jwtToken);
        return authencationResponse;
    }

    public AuthencationResponse registerChauffeur(Chauffeur chauffeurRequest) {
        /*var user = Chauffeur.builder()
                .nom(chauffeurRequest.getNom())
                .login(chauffeurRequest.getLogin())
                .prenom(chauffeurRequest.getPrenom())
                .couleurDuVehicule(chauffeurRequest.getCouleurDuVehicule())
                .secteur(chauffeurRequest.getSecteur())
                .immatriculationDuVehicule(chauffeurRequest.getImmatriculationDuVehicule())
                .typeDeVehicules(chauffeurRequest.getTypeDeVehicules())
                .build();*/

        Chauffeur chauffeur = saveChauffeur(chauffeurRequest);
        jwtToken = jwtService.generateToken(chauffeur);
        authencationResponse.setUserType(UserType.CHAUFFEUR);
        authencationResponse.setLogin(chauffeur.getLogin());
        tokenRepository.save(UserToken.builder().token(jwtToken).build());

        authencationResponse.setToken(jwtToken);
        return authencationResponse;
    }
}
