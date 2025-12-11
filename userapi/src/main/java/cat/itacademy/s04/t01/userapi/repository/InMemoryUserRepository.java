package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private List<User> usersList;

    public InMemoryUserRepository() {
        this.usersList = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        usersList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return usersList;
    }

    @Override
    public Optional<User> findById(UUID id) {

        for (User userId : usersList){
            if (userId.getId().equals(id)){
                return  Optional.of(userId);
            }
        }
       return Optional.empty();
    }
    //  return usersList.stream()
    //            .filter(u -> u.getId().equals(id))
    //            .findFirst();

    @Override
    public List<User> searchByName(String name) {
        List <User> list = usersList.stream()
                .filter((user -> user.getName().equalsIgnoreCase(name)))
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean finded = usersList.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));

        return finded;
    }
}
