package service;

import cat.itacademy.s04.t01.userapi.exceptions.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import cat.itacademy.s04.t01.userapi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    // Simulem el repositori. Així no cal base de dades real.
    @Mock
    private UserRepository userRepository;

    // Creem una instància real de la classe a provar (UserServiceImpl).
    // Els mocks definits a dalt s’injectaran aquí automàticament.

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        // GIVEN:
        User userTest = new User("Pepi", "pepiMola@gmail.com");

        // - El repositori retorna true quan comprovem si existeix l’email
        Mockito.when(userRepository.existsByEmail("pepiMola@gmail.com"))
                .thenReturn(true);

        // WHEN:
        // - Intentem crear un usuari amb aquest email usant el Servei
        assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.createUser(userTest)
        );

        // THEN:
        // - Comprovar que es llança una excepció EmailAlreadyExistsException
        // - Verificar que NO s’ha cridat al mètode save() del repository

        Mockito.verify(userRepository, Mockito.never())
                .save(Mockito.any(User.class));
    }

    /*
    Fes un segon test que comprovi que:
    Es genera un UUID.
    L’usuari es desa correctament si l’email no està repetit.
     */


    @Test
    void createUser_shouldCreateUuid_andSaveNewUser() {
        // - El repositori retorna FALSE quan comprovem si existeix l’email
        User user1 = new User("Lara", "croft@gmail.com");

        Mockito.when(userRepository.existsByEmail("croft@gmail.com"))
                .thenReturn(false);

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User createdUser = userService.createUser(user1);

        assertNotNull(createdUser.getId());
        Mockito.verify(userRepository).save(Mockito.any(User.class));

    }

}
