package com.wanna_wanna.server.service;

import com.wanna_wanna.server.model.WList;
import com.wanna_wanna.server.model.WUser;
import com.wanna_wanna.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<WUser> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<WUser> getUserById(UUID id) {
    return userRepository.findById(id);
  }

  public Set<WList> getListsByUserId(UUID id) {
    Optional<WUser> user = userRepository.findById(id);
    return user.map(WUser::getLists).orElse(null);
  }
}
