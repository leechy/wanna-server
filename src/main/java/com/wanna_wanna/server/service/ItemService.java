package com.wanna_wanna.server.service;

import com.wanna_wanna.server.dto.CreateItemRequest;
import com.wanna_wanna.server.dto.CreateListItemRequest;
import com.wanna_wanna.server.dto.UpdateItemRequest;
import com.wanna_wanna.server.dto.UpdateListItemRequest;
import com.wanna_wanna.server.exception.ItemNotFoundException;
import com.wanna_wanna.server.exception.ListItemNotFoundException;
import com.wanna_wanna.server.exception.ListNotFoundException;
import com.wanna_wanna.server.model.WItem;
import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.model.WListItem;
import com.wanna_wanna.server.repository.ItemRepository;
import com.wanna_wanna.server.repository.ListItemRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ListItemRepository listItemRepository;

  @Autowired
  private ListService listService;

  public WItem createItem(CreateItemRequest request) {
    WItem item = new WItem();
    item.setName(request.getName());
    item.setUnits(request.getUnits());
    item.setType(request.getType());
    item.setPublic(request.isPublic());
    item.setCreatedAt(new Date());
    item.setUpdatedAt(new Date());

    return itemRepository.save(item);
  }

  private WItem getItemByIdOrCreateNew(CreateListItemRequest request) {
    return (request.getItemId() == null)
        // if there is no item id, create a new one
        ? createItem(request)
        : itemRepository.findById(request.getItemId())
            .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + request.getItemId()));
  }

  public WListItem createListItem(CreateListItemRequest request) {
    WItem item = getItemByIdOrCreateNew(request);

    WList list = listService.getListById(request.getListId())
        .orElseThrow(() -> new ListNotFoundException("List with id: " + request.getListId() + " not found"));

    WListItem listItem = new WListItem();
    listItem.setName(request.getName() == null ? item.getName() : request.getName());
    listItem.setType(request.getType() == null ? item.getType() : request.getType());
    listItem.setUnits(request.getUnits() == null ? item.getUnits() : request.getUnits());
    listItem.setQuantity(request.getQuantity());
    listItem.setDeadline(request.getDeadline());
    listItem.setSortOrder(request.getSortOrder());
    listItem.setAssignee(request.getAssignee());
    listItem.setList(list);
    listItem.setItem(item);
    listItem.setCreatedAt(new Date());
    listItem.setUpdatedAt(new Date());

    return listItemRepository.save(listItem);
  }

  public Optional<WItem> getItemById(UUID id) {
    return itemRepository.findById(id);
  }

  public List<WListItem> getListItems(UUID listId) {
    return listItemRepository.findByListId(listId);
  }

  public WItem updateItem(UUID id, UpdateItemRequest request) {
    WItem item = itemRepository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));

    if (request.getName() != null) {
      item.setName(request.getName());
    }
    if (request.getUnits() != null) {
      item.setUnits(request.getUnits());
    }
    if (request.getType() != null) {
      item.setType(request.getType());
    }
    if (request.getIsPublic() != null) {
      item.setPublic(request.getIsPublic());
    }

    item.setUpdatedAt(new Date());
    return itemRepository.save(item);
  }

  public WListItem updateListItem(UUID id, UpdateListItemRequest request) {
    WListItem listItem = listItemRepository.findById(id)
        .orElseThrow(() -> new ListItemNotFoundException("List item not found with id: " + id));

    if (request.getName() != null) {
      listItem.setName(request.getName());
    }
    if (request.getQuantity() != null) {
      listItem.setQuantity(request.getQuantity());
    }
    if (request.getDeadline() != null) {
      listItem.setDeadline(request.getDeadline());
    }
    if (request.getSortOrder() != null) {
      listItem.setSortOrder(request.getSortOrder());
    }
    if (request.getAssignee() != null) {
      listItem.setAssignee(request.getAssignee());
    }
    if (request.getCompleted() != null) {
      listItem.setCompleted(request.getCompleted());
    }

    listItem.setUpdatedAt(new Date());
    return listItemRepository.save(listItem);
  }

  public void deleteItem(UUID id) {
    WItem item = itemRepository.findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
    item.setDeleted(true);
    item.setUpdatedAt(new Date());
    itemRepository.save(item);
  }

  public void deleteListItem(UUID id) {
    WListItem listItem = listItemRepository.findById(id)
        .orElseThrow(() -> new ListItemNotFoundException("List item not found with id: " + id));
    listItem.setDeleted(true);
    listItem.setUpdatedAt(new Date());
    listItemRepository.save(listItem);
  }
}
