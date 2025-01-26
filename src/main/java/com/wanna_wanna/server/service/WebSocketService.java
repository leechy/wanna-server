package com.wanna_wanna.server.service;

import java.util.UUID;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
  private final SimpMessagingTemplate messagingTemplate;

  public WebSocketService(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void notifyListUpdate(UUID listId) {
    messagingTemplate.convertAndSend(
        "/topic/lists/" + listId,
        "List updated");
  }

  public void notifyItemUpdate(UUID listId, UUID itemId) {
    messagingTemplate.convertAndSend(
        "/topic/lists/" + listId + "/items/" + itemId,
        "Item updated");
  }
}
