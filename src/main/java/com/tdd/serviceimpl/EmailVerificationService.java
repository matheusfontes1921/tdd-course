package com.tdd.serviceimpl;

import com.tdd.model.User;

public interface EmailVerificationService {
    void scheduledEmailConfirmation(User user);
}
