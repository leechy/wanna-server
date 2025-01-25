package com.wanna_wanna.server.service;

import com.wanna_wanna.server.dto.CreateListRequest;
import com.wanna_wanna.server.dto.UpdateListRequest;
import com.wanna_wanna.server.exception.ListNotFoundException;
import com.wanna_wanna.server.exception.UserNotFoundException;
import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.model.WUser;
import com.wanna_wanna.server.repository.ListRepository;
import com.wanna_wanna.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {
  @Autowired
  private ListRepository listRepository;

  @Autowired
  private UserRepository userRepository;

  public List<WList> getAllLists() {
    return listRepository.findAll();
  }

  public Optional<WList> getListById(UUID id) {
    return listRepository.findById(id);
  }

  public WList createList(CreateListRequest request) {
    WUser user = userRepository.findById(request.getUid())
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUid()));

    WList list = new WList();

    UUID shareId = request.getShareId() != null ? request.getShareId() : UUID.randomUUID();
    list.setShareId(shareId);

    list.setName(request.getName());

    list.setType(request.getType());

    if (request.getDeadline() != null) {
      list.setDeadline(request.getDeadline());
    }

    list.setNotifyOnListShared(request.isNotifyOnListShared());
    list.setNotifyOnListItemsUpdate(request.isNotifyOnListItemsUpdate());
    list.setNotifyOnItemStateUpdate(request.isNotifyOnItemStateUpdate());

    list.setCreatedAt(new Date());
    list.setUpdatedAt(new Date());
    list = listRepository.save(list);

    user.getLists().add(list);
    userRepository.save(user);

    return list;
  }

  public WList updateList(UUID id, UpdateListRequest request) {
    WList list = listRepository.findById(id)
        .orElseThrow(() -> new ListNotFoundException("List with id: " + id + " not found!"));

    if (request.getName() != null) {
      list.setName(request.getName());
    }
    if (request.getType() != null) {
      list.setType(request.getType());
    }
    if (request.getDeadline() != null) {
      list.setDeadline(request.getDeadline());
    }
    if (request.getNotifyOnListShared() != null) {
      list.setNotifyOnListShared(request.getNotifyOnListShared());
    }
    if (request.getNotifyOnListItemsUpdate() != null) {
      list.setNotifyOnListItemsUpdate(request.getNotifyOnListItemsUpdate());
    }
    if (request.getNotifyOnItemStateUpdate() != null) {
      list.setNotifyOnItemStateUpdate(request.getNotifyOnItemStateUpdate());
    }

    list.setUpdatedAt(new Date());
    return listRepository.save(list);
  }
}
