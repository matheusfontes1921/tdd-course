package com.tdd.data;

import com.tdd.model.User;

public interface UsersRepository {
    boolean save(User user); /* true if persisted and false if not */
}
