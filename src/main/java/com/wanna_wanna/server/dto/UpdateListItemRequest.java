package com.wanna_wanna.server.dto;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class UpdateListItemRequest {
  private String name;
  private Float quantity;
  private Date deadline;
  private Integer sortOrder;
  private UUID assignee;
  private Boolean completed;
}
