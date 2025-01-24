package com.wanna_wanna.server.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {
  private UUID uid;
  private String names;
  private boolean notifyOnListShared = true;
  private boolean notifyOnListItemsUpdate = true;
  private boolean notifyOnItemStateUpdate = true;
  private String expoPushToken;
  private String devicePushToken;
  private boolean deleted = false;
  private Date createdAt;
  private Date updatedAt;
}
