package com.github.tshtk.userservice.endpoint;

import com.github.tshtk.userservice.jaxb.CreateUserRequest;
import com.github.tshtk.userservice.jaxb.CreateUserResponse;
import com.github.tshtk.userservice.jaxb.DeleteUserRequest;
import com.github.tshtk.userservice.jaxb.DeleteUserResponse;
import com.github.tshtk.userservice.jaxb.GetAllUsersResponse;
import com.github.tshtk.userservice.jaxb.GetUserRequest;
import com.github.tshtk.userservice.jaxb.GetUserResponse;
import com.github.tshtk.userservice.jaxb.UpdateUserRequest;
import com.github.tshtk.userservice.jaxb.UpdateUserResponse;
import com.github.tshtk.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class UsersEndpoint {
    private static final String NAMESPACE_URI = "http://github.com.tshtk/userservice";
    private final UserService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers() {
        return service.getUsers();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        return service.getUser(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        return service.deleteUser(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        return service.createUser(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        return service.updateUser(request);
    }
}
