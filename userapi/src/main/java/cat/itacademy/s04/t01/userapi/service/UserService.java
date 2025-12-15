package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser (User user);
    List <User> getAllUser ();
    List <User> getUserByName (String name);
    User getUserById (UUID id);
    boolean existsByEmail (String email);
}
