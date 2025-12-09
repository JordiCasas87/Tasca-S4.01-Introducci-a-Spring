package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.exceptions.NotFoundId;
import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class UserController {

    static ArrayList <User> userList = new ArrayList<>();

    @GetMapping("/users")
    public ArrayList<User> showUsers (){
        return userList;
    }

    @PostMapping ("/users")
    public User createUser (@RequestBody User user){
        User newUser = new User(user.getName(), user.getEmail());
        userList.add(newUser);
        return newUser;
    }

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
