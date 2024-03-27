package com.tdd.serviceimpl;

import com.tdd.model.User;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatedPassword) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("User's first name is empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("User's last name is empty");
        }
        if (!(password.equals(repeatedPassword))) {
            throw new IllegalArgumentException("The passwords are not matching");
        }
        User user = new User(firstName, lastName, email, UUID.randomUUID().toString());
        usersRepository.save(user);
        return user;
    }
}
