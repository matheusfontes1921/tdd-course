package com.tdd.serviceimpl;

import com.tdd.model.User;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatedPassword) {
        return new User(firstName, lastName, email);
    }
}
