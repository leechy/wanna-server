package com.wanna_wanna.server.service;

import com.wanna_wanna.server.dto.CreateListRequest;
import com.wanna_wanna.server.dto.ListWithUsersDTO;
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
import java.util.stream.Collectors;

@Service
public class ListService {
  @Autowired
  private ListRepository listRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

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

  public ListWithUsersDTO getListByShareId(UUID shareId) {
    WList list = listRepository.findByShareId(shareId)
        .orElseThrow(() -> new ListNotFoundException("List with shareId: " + shareId + " not found!"));

    return convertToListWithUsersDTO(list);
  }

  public ListWithUsersDTO joinList(UUID shareId, UUID userId) {
    WList list = listRepository.findByShareId(shareId)
        .orElseThrow(() -> new ListNotFoundException("List with shareId: " + shareId + " not found!"));

    WUser user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found!"));

    // Check if user is already in the list
    if (user.getLists().contains(list)) {
      return convertToListWithUsersDTO(list);
    }

    // Add list to user's lists
    user.getLists().add(list);
    userRepository.save(user);

    return convertToListWithUsersDTO(list);
  }

  private ListWithUsersDTO convertToListWithUsersDTO(WList list) {
    ListWithUsersDTO dto = new ListWithUsersDTO();
    dto.setId(list.getId());
    dto.setName(list.getName());
    dto.setShareId(list.getShareId());
    dto.setType(list.getType());
    dto.setDeadline(list.getDeadline());
    dto.setCreatedAt(list.getCreatedAt());
    dto.setUpdatedAt(list.getUpdatedAt());
    dto.setUsers(list.getUsers().stream()
        .map(userService::convertToSimpleUserDTO)
        .collect(Collectors.toSet()));
    return dto;
  }
}
