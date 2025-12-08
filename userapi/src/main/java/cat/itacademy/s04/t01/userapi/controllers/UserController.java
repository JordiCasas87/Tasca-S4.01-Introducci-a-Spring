package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {

    static ArrayList <User> userList = new ArrayList<>();

    @GetMapping("/users")
    public ArrayList<User> showUsers (){
        return userList;
    }


}
