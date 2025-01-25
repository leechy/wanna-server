package com.wanna_wanna.server.dto;

import lombok.Data;

@Data
public class UpdateItemRequest {
  private String name;
  private String units;
  private String type;
  private Boolean isPublic = true;
}
