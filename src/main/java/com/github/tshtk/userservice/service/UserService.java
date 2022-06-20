package com.github.tshtk.userservice.service;

import com.github.tshtk.userservice.entity.RoleEntity;
import com.github.tshtk.userservice.entity.UserEntity;
import com.github.tshtk.userservice.error.ErrorsMessage;
import com.github.tshtk.userservice.jaxb.CreateUserRequest;
import com.github.tshtk.userservice.jaxb.CreateUserResponse;
import com.github.tshtk.userservice.jaxb.DeleteUserRequest;
import com.github.tshtk.userservice.jaxb.DeleteUserResponse;
import com.github.tshtk.userservice.jaxb.ErrorsDto;
import com.github.tshtk.userservice.jaxb.GetAllUsersDto;
import com.github.tshtk.userservice.jaxb.GetAllUsersResponse;
import com.github.tshtk.userservice.jaxb.GetUserRequest;
import com.github.tshtk.userservice.jaxb.GetUserResponse;
import com.github.tshtk.userservice.jaxb.UpdateUserRequest;
import com.github.tshtk.userservice.jaxb.UpdateUserResponse;
import com.github.tshtk.userservice.mapper.UserEntityMapper;
import com.github.tshtk.userservice.repository.UserRepository;
import com.github.tshtk.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserEntityMapper mapper;
    private final UserValidator validator;

    public GetAllUsersResponse getUsers() {
        final List<UserEntity> userEntityList = userRepository.findAll();
        final List<GetAllUsersDto> userShortList = mapper.toGetAllUsersDto(userEntityList);
        final GetAllUsersResponse response = new GetAllUsersResponse();
        response.getUser().addAll(userShortList);
        return response;
    }


    public GetUserResponse getUser(final GetUserRequest request) {
        final GetUserResponse response = new GetUserResponse();
        final String login = request.getLogin();
        final Optional<UserEntity> userEntity = userRepository.findById(login);
        if (!userEntity.isPresent()) {
            response.setSuccess(false);
            response.setErrors(new ErrorsDto());
            response.getErrors().getError().add(ErrorsMessage.USER_NOT_FOUND.getMessage() + login);
            return response;
        }
        response.setSuccess(true);
        response.setUser(mapper.toGetUserDto(userEntity.get()));
        return response;
    }


    public DeleteUserResponse deleteUser(final DeleteUserRequest request) {
        final DeleteUserResponse response = new DeleteUserResponse();
        final String login = request.getLogin();
        final Optional<UserEntity> userEntity = userRepository.findById(login);
        if (!userEntity.isPresent()) {
            response.setSuccess(false);
            response.setErrors(new ErrorsDto());
            response.getErrors().getError().add(ErrorsMessage.USER_NOT_FOUND.getMessage() + login);
            return response;
        }
        userRepository.delete(userEntity.get());
        response.setSuccess(true);
        return response;
    }


    public CreateUserResponse createUser(final CreateUserRequest request) {
        final CreateUserResponse response = new CreateUserResponse();

        final List<String> errors = validator.validate(request);
        final List<RoleEntity> roles = roleService.getRolesByName(request.getRoleName(), errors);

        if (!errors.isEmpty()) {
            response.setSuccess(false);
            response.setErrors(new ErrorsDto());
            response.getErrors().getError().addAll(errors);
            return response;
        }

        userRepository.save(mapper.fromCreateUserRequest(request, roles));
        response.setSuccess(true);
        return response;
    }


    public UpdateUserResponse updateUser(final UpdateUserRequest request) {
        final UpdateUserResponse response = new UpdateUserResponse();

        final List<String> errors = validator.validate(request);

        final String login = request.getLogin();
        final String name = request.getName();
        final String password = request.getPassword();
        final List<RoleEntity> roles = new ArrayList<>();

        final Optional<UserEntity> optionalUserEntity = userRepository.findById(login);

        if (!optionalUserEntity.isPresent()) {
            errors.add(ErrorsMessage.USER_NOT_FOUND.getMessage() + login);
        }

        if (request.getRoleName() != null && !request.getRoleName().isEmpty()) {
            roles.addAll(roleService.getRolesByName(request.getRoleName(), errors));
        }

        if (!errors.isEmpty()) {
            response.setSuccess(false);
            response.setErrors(new ErrorsDto());
            response.getErrors().getError().addAll(errors);
            return response;
        }

        final UserEntity userEntity = optionalUserEntity.get();
        if (name != null && !name.isEmpty()) {
            userEntity.setName(name);
        }
        if (password != null && !password.isEmpty()) {
            userEntity.setPassword(password);
        }
        if (!roles.isEmpty()) {
            userEntity.setRoles(roles);
        }
        userRepository.save(userEntity);
        response.setSuccess(true);
        return response;
    }
}
