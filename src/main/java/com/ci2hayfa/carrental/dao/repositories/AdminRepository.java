package com.ci2hayfa.carrental.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ci2hayfa.carrental.dao.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
 Admin findByUsername(String username);
}

