package com.tdd.serviceimpl;

import com.tdd.model.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password, String repeatedPassword);
}
