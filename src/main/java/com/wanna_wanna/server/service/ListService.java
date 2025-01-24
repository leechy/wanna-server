package com.wanna_wanna.server.service;

import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {
  @Autowired
  private ListRepository listRepository;

  public List<WList> getAllLists() {
    return listRepository.findAll();
  }

  public Optional<WList> getListById(UUID id) {
    return listRepository.findById(id);
  }
}
