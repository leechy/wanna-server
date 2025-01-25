package com.wanna_wanna.server.controller;

import com.wanna_wanna.server.dto.CreateItemRequest;
import com.wanna_wanna.server.dto.CreateListItemRequest;
import com.wanna_wanna.server.dto.UpdateItemRequest;
import com.wanna_wanna.server.dto.UpdateListItemRequest;
import com.wanna_wanna.server.model.WItem;
import com.wanna_wanna.server.model.WListItem;
import com.wanna_wanna.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ItemController {
  @Autowired
  private ItemService itemService;

  @PostMapping("/items")
  public WItem createItem(@RequestBody CreateItemRequest request) {
    return itemService.createItem(request);
  }

  @GetMapping("/items/{id}")
  public Optional<WItem> getItemById(@PathVariable("id") UUID id) {
    return itemService.getItemById(id);
  }

  @PostMapping("/lists/{listId}/items")
  public WListItem createListItem(
      @PathVariable("listId") UUID listId,
      @RequestBody CreateListItemRequest request) {
    request.setListId(listId);
    return itemService.createListItem(request);
  }

  @GetMapping("/lists/{listId}/items")
  public List<WListItem> getListItems(@PathVariable("listId") UUID listId) {
    return itemService.getListItems(listId);
  }

  @PatchMapping("/items/{id}")
  public WItem updateItem(
      @PathVariable("id") UUID id,
      @RequestBody UpdateItemRequest request) {
    return itemService.updateItem(id, request);
  }

  @DeleteMapping("/items/{id}")
  public void deleteItem(@PathVariable("id") UUID id) {
    itemService.deleteItem(id);
  }

  @PatchMapping("/lists/{listId}/items/{id}")
  public WListItem updateListItem(
      @PathVariable("listId") UUID listId,
      @PathVariable("id") UUID id,
      @RequestBody UpdateListItemRequest request) {
    return itemService.updateListItem(id, request);
  }

  @DeleteMapping("/lists/{listId}/items/{id}")
  public void deleteListItem(
      @PathVariable("listId") UUID listId,
      @PathVariable("id") UUID id) {
    itemService.deleteListItem(id);
  }
}
