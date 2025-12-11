package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryUserRepositoryTest {

    @Test
    public void saveUserInList_ListSizeChange(){
        InMemoryUserRepository repoTest = new InMemoryUserRepository();
        User user = new User("Javier","javier@msn.es");

        repoTest.save(user);

        assertEquals(1,repoTest.findAll().size(),"La lista no ha incrementado su tamaño");
    }

    @Test
    public void FindUserById_returnsCorrectUser(){
        InMemoryUserRepository repoTest = new InMemoryUserRepository();
        User user = new User("Javier","javier@msn.es");

        repoTest.save(user);
        Optional<User> findId = repoTest.findById(user.getId());

        assertTrue(findId.isPresent());
    }


}
