package com.tdd.serviceimpl;

import com.tdd.model.User;
import com.tdd.serviceimpl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatedPassword;
    @BeforeEach
    void init() {
        userService = new UserServiceImpl();
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

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        //Assert
        assertNotNull(user, "The created user should not return null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(),"User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId(), "User id should not be null");
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

}
