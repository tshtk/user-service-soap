package com.github.tshtk.userservice.repository;

import com.github.tshtk.userservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
