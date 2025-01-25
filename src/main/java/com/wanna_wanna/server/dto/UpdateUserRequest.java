package com.wanna_wanna.server.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
  private String names;
  private Boolean notifyOnListShared;
  private Boolean notifyOnListItemsUpdate;
  private Boolean notifyOnItemStateUpdate;
  private String expoPushToken;
  private String devicePushToken;
}
