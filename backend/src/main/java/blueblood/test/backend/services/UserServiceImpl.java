package blueblood.test.backend.services;

import blueblood.test.backend.entities.Utilisateur;
import blueblood.test.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Utilisateur> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
