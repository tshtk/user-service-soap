package com.github.tshtk.userservice.service;

import com.github.tshtk.userservice.entity.RoleEntity;
import com.github.tshtk.userservice.error.ErrorsMessage;
import com.github.tshtk.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository repository;

    public List<RoleEntity> getRolesByName(final List<String> roleNames, final List<String> errors) {
        List<RoleEntity> roles = new ArrayList<>();
        Map<String, RoleEntity> existingRoles = repository.findAll()
            .stream()
            .collect(Collectors.toMap(RoleEntity::getName, o -> o));

        for (String roleName : roleNames) {
            if (!existingRoles.containsKey(roleName)) {
                errors.add(ErrorsMessage.ROLE_NOT_FOUND.getMessage() + roleName);
                continue;
            }
            roles.add(existingRoles.get(roleName));
        }
        return roles;
    }
}
