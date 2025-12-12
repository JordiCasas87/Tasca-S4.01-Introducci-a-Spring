package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryUserRepositoryTest {

    private User user;
    private InMemoryUserRepository repoTest;

    @BeforeEach
            void setUp () {
       repoTest = new InMemoryUserRepository();
       user = new User("Javier", "javier@msn.es");
    }

    @Test
    public void saveUserInList_ListSizeChange(){

        repoTest.save(user);

        assertEquals(1,repoTest.findAll().size(),"La lista no ha incrementado su tamaño");
    }

    @Test
    public void FindUserById_returnsCorrectUser(){

        repoTest.save(user);
        Optional<User> findId = repoTest.findById(user.getId());

        assertTrue(findId.isPresent());
    }

    @Test
    public void FindUserByName_returnUser(){

        User user1 = new User("Java","pepelu@msn.es");
        User user2 = new User("Maria","pepelu@msn.es");

        repoTest.save(user);
        repoTest.save(user1);
        repoTest.save(user2);

        List<User> list = repoTest.searchByName("Ja");
        assertEquals(2,list.size());

    }

    @Test
    public void FindUserByEmail_returnUser(){
        
        User user1 = new User("Java","pepelu@msn.es");
        User user2 = new User("Maria","pepelu@msn.es");

        repoTest.save(user);
        repoTest.save(user1);
        repoTest.save(user2);

        boolean finded= repoTest.existsByEmail("javier@msn.es");

        assertTrue(finded);

    }

}
