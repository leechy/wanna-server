package com.wanna_wanna.server.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateListRequest {
  private String name;
  private UUID uid;
  private UUID shareId;
  private String type = "project";
  private Date deadline;
  private boolean notifyOnListShared = true;
  private boolean notifyOnListItemsUpdate = true;
  private boolean notifyOnItemStateUpdate = true;
}
