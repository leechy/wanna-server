package com.wanna_wanna.server.controller;

import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.model.WUser;
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
  public List<WUser> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{uid}")
  public Optional<WUser> getUserById(@PathVariable("uid") UUID uid) {
    return userService.getUserById(uid);
  }

  @GetMapping("/{uid}/lists")
  public Set<WList> getListsByUserId(@PathVariable("uid") UUID uid) {
    return userService.getListsByUserId(uid);
  }
}
