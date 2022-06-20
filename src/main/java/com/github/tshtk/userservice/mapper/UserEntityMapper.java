package com.github.tshtk.userservice.mapper;

import com.github.tshtk.userservice.entity.RoleEntity;
import com.github.tshtk.userservice.entity.UserEntity;
import com.github.tshtk.userservice.jaxb.CreateUserRequest;
import com.github.tshtk.userservice.jaxb.GetAllUsersDto;
import com.github.tshtk.userservice.jaxb.GetUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface UserEntityMapper {
    List<GetAllUsersDto> toGetAllUsersDto(List<UserEntity> userEntities);

    @Mapping(target = "role", source = "roles")
    GetUserDto toGetUserDto(UserEntity userEntity);

    default String map(RoleEntity roleEntity) {
        return roleEntity.getName();
    }

    UserEntity fromCreateUserRequest(CreateUserRequest request, List<RoleEntity> roles);


}
