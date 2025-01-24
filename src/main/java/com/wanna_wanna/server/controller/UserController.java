package com.wanna_wanna.server.controller;

import com.wanna_wanna.server.dto.UserDTO;
import com.wanna_wanna.server.dto.UserWithListsDTO;
import com.wanna_wanna.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserDTO> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{uid}")
  public Optional<UserDTO> getUserById(@PathVariable("uid") UUID uid) {
    return userService.getUserById(uid);
  }

  @GetMapping("/{uid}/lists")
  public Set<UserWithListsDTO> getListsByUserId(@PathVariable("uid") UUID uid) {
    return userService.getListsByUserId(uid);
  }
}
