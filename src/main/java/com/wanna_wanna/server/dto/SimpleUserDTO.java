package com.wanna_wanna.server.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class SimpleUserDTO {
  private UUID uid;
  private String names;
}
