package blueblood.test.backend.services;

import blueblood.test.backend.entities.Task;
import blueblood.test.backend.entities.Utilisateur;

import java.util.Optional;

public interface UserService {

    Optional<Utilisateur> getUserById (Long userId);
}
