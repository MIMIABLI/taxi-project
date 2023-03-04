package com.mireille.gestiontaxiapi.auth;

import com.mireille.gestiontaxiapi.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthencationResponse> register(
            @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthencationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request.getLogin() + " est authentifié !");
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

}
