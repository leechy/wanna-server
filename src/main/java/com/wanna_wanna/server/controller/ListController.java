package com.wanna_wanna.server.controller;

import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/lists")
public class ListController {
  @Autowired
  private ListService listService;

  @GetMapping
  public List<WList> getAllLists() {
    return listService.getAllLists();
  }

  @GetMapping("/{id}")
  public Optional<WList> getListById(@PathVariable("id") UUID id) {
    return listService.getListById(id);
  }
}
