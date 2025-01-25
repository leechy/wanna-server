package com.wanna_wanna.server.repository;

import com.wanna_wanna.server.model.WListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListItemRepository extends JpaRepository<WListItem, UUID> {
  List<WListItem> findByListId(UUID listId);
}
