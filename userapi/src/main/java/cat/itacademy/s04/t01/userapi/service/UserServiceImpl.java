package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.exceptions.NotFoundId;
import cat.itacademy.s04.t01.userapi.models.User;

import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
       boolean exist = existsByEmail(user.getEmail());

       if (exist) {
           throw new EmailAlreadyExistsException("El email ya existe");
       }

        return userRepository.save(user);
    }


    @Override
    public List <User> getAllUser () {
        return userRepository.findAll();
    }


    @Override
    public List <User> getUserByName(String name) {
        return userRepository.searchByName(name);
    }

    @Override
    public User getUserById(UUID id) {
        User userFound = userRepository.findById(id)
                .orElseThrow(()->new NotFoundId("Id no encontrado!"));

        return userFound;
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
