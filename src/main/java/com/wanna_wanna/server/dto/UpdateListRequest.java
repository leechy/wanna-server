package com.wanna_wanna.server.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateListRequest {
  private String name;
  private String type;
  private Date deadline;
  private Boolean notifyOnListShared;
  private Boolean notifyOnListItemsUpdate;
  private Boolean notifyOnItemStateUpdate;
}
