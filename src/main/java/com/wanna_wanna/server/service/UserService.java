package com.wanna_wanna.server.service;

import com.wanna_wanna.server.dto.SimpleUserDTO;
import com.wanna_wanna.server.dto.UpdateUserRequest;
import com.wanna_wanna.server.dto.UserDTO;
import com.wanna_wanna.server.dto.UserWithListsDTO;
import com.wanna_wanna.server.exception.UserNotFoundException;
import com.wanna_wanna.server.model.WUser;
import com.wanna_wanna.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList());
  }

  public Optional<UserDTO> getUserById(UUID id) {
    final Optional<UserDTO> user = userRepository.findById(id).map(this::convertToUserDTO);
    if (!user.isPresent()) {
      throw new UserNotFoundException("User with id " + id + " not found.");
    }
    return user;
  }

  public Set<UserWithListsDTO> getListsByUserId(UUID id) {
    final Optional<UserWithListsDTO> user = userRepository.findById(id).map(this::convertToUserWithListsDTO);
    if (!user.isPresent()) {
      throw new UserNotFoundException("User with id " + id + " not found.");
    }

    return user
        .map(java.util.Collections::singleton)
        .orElse(java.util.Collections.emptySet());
  }

  public UserDTO createUser(String names) {
    WUser user = new WUser(names);

    WUser savedUser = userRepository.save(user);
    return convertToUserDTO(savedUser);
  }

  public UserDTO updateUser(UUID uid, UpdateUserRequest request) {
    WUser user = userRepository.findById(uid)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + uid));

    if (request.getNames() != null) {
      user.setNames(request.getNames());
    }
    if (request.getNotifyOnListShared() != null) {
      user.setNotifyOnListShared(request.getNotifyOnListShared());
    }
    if (request.getNotifyOnListItemsUpdate() != null) {
      user.setNotifyOnListItemsUpdate(request.getNotifyOnListItemsUpdate());
    }
    if (request.getNotifyOnItemStateUpdate() != null) {
      user.setNotifyOnItemStateUpdate(request.getNotifyOnItemStateUpdate());
    }
    if (request.getExpoPushToken() != null) {
      user.setExpoPushToken(request.getExpoPushToken());
    }
    if (request.getDevicePushToken() != null) {
      user.setDevicePushToken(request.getDevicePushToken());
    }

    user.setUpdatedAt(new Date());

    WUser updatedUser = userRepository.save(user);
    return convertToUserDTO(updatedUser);
  }

  public UserDTO convertToUserDTO(WUser user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUid(user.getId());
    userDTO.setNames(user.getNames());
    userDTO.setNotifyOnListShared(user.isNotifyOnListShared());
    userDTO.setNotifyOnItemStateUpdate(user.isNotifyOnItemStateUpdate());
    userDTO.setNotifyOnListItemsUpdate(user.isNotifyOnListItemsUpdate());
    userDTO.setExpoPushToken(user.getExpoPushToken());
    userDTO.setDevicePushToken(user.getDevicePushToken());
    userDTO.setDeleted(user.isDeleted());
    userDTO.setCreatedAt(user.getCreatedAt());
    userDTO.setUpdatedAt(user.getUpdatedAt());
    return userDTO;
  }

  private UserWithListsDTO convertToUserWithListsDTO(WUser user) {
    UserWithListsDTO userDTO = new UserWithListsDTO();
    userDTO.setUid(user.getId());
    userDTO.setNames(user.getNames());
    userDTO.setNotifyOnListShared(user.isNotifyOnListShared());
    userDTO.setNotifyOnItemStateUpdate(user.isNotifyOnItemStateUpdate());
    userDTO.setNotifyOnListItemsUpdate(user.isNotifyOnListItemsUpdate());
    userDTO.setExpoPushToken(user.getExpoPushToken());
    userDTO.setDevicePushToken(user.getDevicePushToken());
    userDTO.setDeleted(user.isDeleted());
    userDTO.setCreatedAt(user.getCreatedAt());
    userDTO.setUpdatedAt(user.getUpdatedAt());
    userDTO.setLists(user.getLists());
    return userDTO;
  }

  public SimpleUserDTO convertToSimpleUserDTO(WUser user) {
    SimpleUserDTO userDTO = new SimpleUserDTO();
    userDTO.setUid(user.getId());
    userDTO.setNames(user.getNames());
    return userDTO;
  }
}
