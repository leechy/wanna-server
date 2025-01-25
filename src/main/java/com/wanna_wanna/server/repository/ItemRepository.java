package com.wanna_wanna.server.repository;

import com.wanna_wanna.server.model.WItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<WItem, UUID> {
}
