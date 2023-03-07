package com.mireille.gestiontaxiapi.auth;

import com.mireille.gestiontaxiapi.config.JwtService;
import com.mireille.gestiontaxiapi.models.Chauffeur;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthencationResponse> register(
            @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/registeradmin")
    public ResponseEntity<AuthencationResponse> registerAdmin(
            @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.registerAdmin(request), HttpStatus.CREATED);
    }

    @PostMapping("/registerchauffeur")
    public ResponseEntity<AuthencationResponse> registerChauffeur(
            @RequestBody Chauffeur chauffeurRequest) {
        return new ResponseEntity<>(authenticationService.registerChauffeur(chauffeurRequest), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthencationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request.getLogin() + " est authentifié !");
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

}
