package com.wanna_wanna.server.repository;

import com.wanna_wanna.server.model.WUser;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<WUser, UUID> {
}
