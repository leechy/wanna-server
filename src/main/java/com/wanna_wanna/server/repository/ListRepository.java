package com.wanna_wanna.server.repository;

import com.wanna_wanna.server.model.WList;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<WList, UUID> {
  Optional<WList> findByShareId(@Param("shareId") UUID shareId);
}
