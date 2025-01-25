package com.wanna_wanna.server.dto;

import lombok.Data;

@Data
public class CreateItemRequest {
  private String name;
  private String units;
  private String type = "task";
  private boolean isPublic = false;
}
