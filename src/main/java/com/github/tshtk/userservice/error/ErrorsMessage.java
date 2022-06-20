package com.github.tshtk.userservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorsMessage {
    FIELD_LOGIN_IS_EMPTY("field login is empty"),
    FIELD_NAME_IS_EMPTY("field name is empty"),
    FIELD_PASSWORD_IS_EMPTY("field password is empty"),
    FIELD_ROLE_NAME_IS_EMPTY("field role name is empty"),
    INCORRECT_FORMAT_PASSWORD("incorrect format password"),
    LOGIN_ALREADY_EXIST("login already exist: "),
    USER_NOT_FOUND("user not found: "),
    ROLE_NOT_FOUND("role not found: ");

    private final String message;
}
