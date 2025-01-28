package blueblood.test.backend.controllers;

import blueblood.test.backend.DTOs.AuthRequest;
import blueblood.test.backend.DTOs.LoginResponse;
import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.services.AuthenticationService;
import blueblood.test.backend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Utilisateur> register(@RequestBody AuthRequest registerUserDto) {
        Utilisateur registeredUtilisateur = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUtilisateur);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody AuthRequest loginUserDto) {
        Utilisateur authenticatedUtilisateur = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUtilisateur);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
