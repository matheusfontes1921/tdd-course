package com.tdd.serviceimpl;

import com.tdd.model.User;
import com.tdd.serviceimpl.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @DisplayName("Create User")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        //Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Matheus";
        String lastName = "Silva";
        String email = "test@test";
        String password = "123456789";
        String repeatedPassword = "123456789";

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatedPassword);

        //Assert
        assertNotNull(user, "The created user should not return null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect");
        assertEquals(lastName, user.getLastName(),"User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
    }

}
