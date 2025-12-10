package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.NotFoundId;
import cat.itacademy.s04.t01.userapi.exceptions.NotFoundName;
import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class UserController {

    static ArrayList <User> userList = new ArrayList<>();

    //obtener toda la lista de usuario o solo el usuario filtrado por nombre.
    //mismo endpoint se usa para dos cosas.

    @GetMapping("/users")
    public ArrayList<User> showUserByName(@RequestParam (required = false) String name) {

        if (name == null){
            return userList;
        }

        ArrayList<User> userFinded = new ArrayList<>();

        for (User userByName : userList) {
            if (userByName.getName().toLowerCase().contains(name.toLowerCase())) {
                userFinded.add(userByName);
                return userFinded;
            }
        }
        throw new NotFoundName("Nombre no encontrado");
    }

    //crear usuario Post

    @PostMapping ("/users")
    public User createUser (@RequestBody User user){
        User newUser = new User(user.getName(), user.getEmail());
        userList.add(newUser);
        return newUser;
    }

    // obtener usuario por id
    @GetMapping("/users/{id}")
    public User getUserId(@PathVariable UUID id) {

        for (User userId : userList) {
            if (userId.getId().equals(id)) {
                return userId;
            }
        }
        throw new NotFoundId("Id no encontrado");
    }




}
