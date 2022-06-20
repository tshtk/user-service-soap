package com.github.tshtk.userservice.validator;

import com.github.tshtk.userservice.error.ErrorsMessage;
import com.github.tshtk.userservice.jaxb.CreateUserRequest;
import com.github.tshtk.userservice.jaxb.UpdateUserRequest;
import com.github.tshtk.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository repository;

    public List<String> validate (final CreateUserRequest request) {
        final List<String> errors = new ArrayList<>();
        final String login = request.getLogin();
        final String name = request.getName();
        final String password = request.getPassword();
        final List<String> roleName = request.getRoleName();
        if (name == null || name.isEmpty()) {
            errors.add(ErrorsMessage.FIELD_NAME_IS_EMPTY.getMessage());
        }
        if (login == null || login.isEmpty()) {
            errors.add(ErrorsMessage.FIELD_LOGIN_IS_EMPTY.getMessage());
        }
        if(repository.existsById(login)) {
            errors.add(ErrorsMessage.LOGIN_ALREADY_EXIST.getMessage() + login);
        }
        if (password == null || password.isEmpty()) {
            errors.add(ErrorsMessage.FIELD_PASSWORD_IS_EMPTY.getMessage());
        }
        if (password != null && !validatePassword(password)) {
            errors.add(ErrorsMessage.INCORRECT_FORMAT_PASSWORD.getMessage());
        }
        if (roleName == null || roleName.isEmpty()) {
            errors.add(ErrorsMessage.FIELD_ROLE_NAME_IS_EMPTY.getMessage());
        }
        return errors;
    }

    public List<String> validate(final UpdateUserRequest request) {
        final List<String> errors = new ArrayList<>();
        final String login = request.getLogin();
        final String password = request.getPassword();
        if (login == null || login.isEmpty()) {
            errors.add(ErrorsMessage.FIELD_LOGIN_IS_EMPTY.getMessage());
        }
        if (password != null && !password.isEmpty() && !validatePassword(password)) {
            errors.add(ErrorsMessage.INCORRECT_FORMAT_PASSWORD.getMessage());
        }
        return errors;
    }

    private boolean validatePassword(final String password) {
        return password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
    }

}
