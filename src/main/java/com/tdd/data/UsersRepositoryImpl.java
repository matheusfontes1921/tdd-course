package com.tdd.data;

import com.tdd.model.User;

import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepository {
    Map<String, User> users = new HashMap<>();
    boolean returnValue = false;
    @Override
    public boolean save(User user) {
        if (!users.containsKey(user.getId())) {
            /*if does not cotain a key, save to the hashmap */
            users.put(user.getId(), user);
            returnValue = true;
        }
        return returnValue;
    }
}
