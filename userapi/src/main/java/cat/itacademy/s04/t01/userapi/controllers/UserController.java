package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //obtener toda la lista de usuario o solo el usuario filtrado por nombre.
    //mismo endpoint se usa para dos cosas.
    @GetMapping("/users")
    public List<User> showUserByName(@RequestParam (required = false) String name) {

        if (name == null){
           List <User> allUsers = userService.getAllUser();
            return allUsers;
        }

        List<User> userByName = userService.getUserByName(name);
        return userByName;

    }


    //crear usuario Post

    @PostMapping ("/users")
    public User createUser (@RequestBody User user) {
        User newUser = new User(user.getName(), user.getEmail());
        return userService.createUser(newUser);
    }


    // obtener usuario por id
    @GetMapping("/users/{id}")
    public Optional<User> getUserId (@PathVariable UUID id){
        return userService.getUserById(id);

    }

    //exites por email booleano

    @GetMapping("/users/email/{email}")
    public Boolean getUserbyEmail (@PathVariable String email){
        return userService.existsByEmail(email);

    }



}
