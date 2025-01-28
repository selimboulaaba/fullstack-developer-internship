package blueblood.test.backend.controllers;

import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<Utilisateur> getUser(Authentication authentication) {
        Long userId = ((Utilisateur) authentication.getPrincipal()).getId();

        Optional<Utilisateur> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }
}
