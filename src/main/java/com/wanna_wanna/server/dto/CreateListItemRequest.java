package com.wanna_wanna.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateListItemRequest extends CreateItemRequest {
  private UUID itemId;
  private UUID listId;
  private Float quantity;
  private Date deadline;
  private Integer sortOrder;
  private UUID assignee;
}
