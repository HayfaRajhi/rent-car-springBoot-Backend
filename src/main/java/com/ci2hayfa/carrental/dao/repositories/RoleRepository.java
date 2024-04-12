package com.ci2hayfa.carrental.dao.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ci2hayfa.carrental.web.models.ERole;
import com.ci2hayfa.carrental.web.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}