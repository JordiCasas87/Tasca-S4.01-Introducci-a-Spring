package cat.itacademy.s04.t01.userapi.controllers;
import cat.itacademy.s04.t01.userapi.models.User;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getUsers_returnsEmptyListInitially() throws Exception{

        // Simula GET /users
        // Espera un array buit
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

    }

    @Test
    void createUser_returnsUserWithId() throws Exception {
        // Simula POST /users amb JSON
        // Espera que torni el mateix usuari amb UUID no nul
    User user = new User("Pepe","pepe@odioElTest.joder");
    String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                )

                .andExpect(status().isOk());

        mockMvc.perform(get("/users?name=Pepe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("Pepe"));
    }

    @Test
    void getUserById_returnsCorrectUser() throws Exception {
        // Primer afegeix un usuari amb POST
        // Després GET /users/{id} i comprova que torni aquest usuari
        User user = new User("Pepe","pepe@odioElTest.joder");
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult result =  mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        )
                        .andExpect(status().isOk())
                                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        User userMapper = objectMapper.readValue(responseJson,User.class);

        UUID id = userMapper.getId();

        mockMvc.perform(get("/users/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pepe"));

    }

    @Test
    void getUserById_returnsNotFoundIfMissing() throws Exception {
        // Simula GET /users/{id} amb un id aleatori
        // Espera 404
        UUID idFalse = UUID.randomUUID();

        User user = new User("Pepe","pepe@odioElTest.joder");
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult result =  mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        User userMapper = objectMapper.readValue(responseJson,User.class);

        UUID id = idFalse;

        mockMvc.perform(get("/users/{id}",id))
                .andExpect(status().isNotFound());


    }

    @Test
    void getUsers_withNameParam_returnsFilteredUsers() throws Exception {
        // Afegeix dos usuaris amb POST
        // Fa GET /users?name=jo i comprova que només torni els que contenen "jo"

        User user = new User("Pepe","pepe@odioElTest.joder");
        String userJson = objectMapper.writeValueAsString(user);
        User user1 = new User("Jordi","Jordi@odioElTest.joder");
        String userJson1 = objectMapper.writeValueAsString(user1);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        );

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson1)
        );

        mockMvc.perform(get("/users?name=jo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jordi"))
                .andExpect(jsonPath("$.length()").value(1));

    }

}
