package servicetest;

import com.tdd.data.UsersRepository;
import com.tdd.model.User;
import com.tdd.serviceimpl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UsersRepository usersRepository;
    @Mock
    EmailVerificationService emailVerificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatedPassword;
    @BeforeEach
    void init() {
        firstName = "Matheus";
        lastName = "Silva";
        email = "test@test";
        password = "123456789";
        repeatedPassword = "123456789";
    }
    @DisplayName("Create User")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        //Arrange
        Mockito.when(usersRepository.save(any(User.class))).thenReturn(true);
        //Mockito.any(User.class) - stubbing a method. it means that any element of user class can be used.
        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        //Assert
        assertNotNull(user, "The created user should not return null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(),"User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId(), "User id should not be null");
        Mockito.verify(usersRepository,times(1)).save(any(User.class)); //ask mockito to verify if the save method is called just one time
    }
    @DisplayName("Empty First Name should throw illegal argument exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException(){
        //Arrange
        String firstName = "";
        String expectedThrown = "User's first name is empty";

        //Act & Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            userService.createUser(firstName,lastName,email,password,repeatedPassword);
        },"Empty first name should throws illegal argument exception");

        //Assert (check if the message is equal in both cases)
        assertEquals(expectedThrown,thrown.getMessage(),"The exception message is not similar");
    }

    @DisplayName("Empty last name should throw illegal argument exception")
    @Test
    void testCreateUser_whenLastNameIsEmpty_throwsIllegalArgumentException(){
        //Arrange
        String lastName = "";
        String expectedThrown = "User's last name is empty";

        //Act & Arrange
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            userService.createUser(firstName,lastName,email,password,repeatedPassword);
        },"Empty last name should throws an illegal argument exception");

        //Arrange
        assertEquals(expectedThrown, thrown.getMessage(), "The exception message is not similar");
    }

    @DisplayName("Comparing the passwords")
    @Test
    void testCreateUser_whenPasswordAndRepeatedPasswordAreDifferent_throwsIllegalArgumentException() {

        //Arrange
        String password = "12345678";
        String repeatedPassword = "123456789";
        String expectedThrown = "The passwords are not matching";

        //Act & Arrange
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName,lastName,email,password,repeatedPassword);
        },"The password is equal to the repeated password. None exception shoudl be thrown.");

        //Arrange
        assertEquals(expectedThrown, thrown.getMessage(), "It should returns a different exception");
    }

    @DisplayName("UserServiceException")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException(){
        //Arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        //Act
        assertThrows(UserServiceException.class, ()-> {
            userService.createUser(firstName,lastName,email,password,repeatedPassword);
        },"Should have thrown user service exception");
    }

    @DisplayName("EmailNotificationException is handled")
    @Test
    void testCreateUser_whenEmailNotificationExceptionIsThrown_throwsUserServiceException(){
        //Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);
        doThrow(EmailNotificationServiceException.class).when(emailVerificationService).scheduledEmailConfirmation(any(User.class));

        //Act & Assert
        assertThrows(UserServiceException.class, ()-> {
            userService.createUser(firstName,lastName,email,password,repeatedPassword);
        }, "Should have thrown Email Notification Service Exception");

        //Assert
        verify(emailVerificationService,times(1)).scheduledEmailConfirmation(any(User.class));
    }

}
