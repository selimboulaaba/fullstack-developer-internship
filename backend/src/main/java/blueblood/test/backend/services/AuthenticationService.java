package blueblood.test.backend.services;

import blueblood.test.backend.DTOs.AuthRequest;
import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur signup(AuthRequest input) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(input.getUsername());
        utilisateur.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(utilisateur);
    }

    public Utilisateur authenticate(AuthRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
