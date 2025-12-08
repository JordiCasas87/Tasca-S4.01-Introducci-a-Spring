package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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


}
