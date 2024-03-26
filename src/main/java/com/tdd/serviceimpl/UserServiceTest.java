package com.tdd.serviceimpl;

import com.tdd.model.User;
import com.tdd.serviceimpl.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
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
    }

}
