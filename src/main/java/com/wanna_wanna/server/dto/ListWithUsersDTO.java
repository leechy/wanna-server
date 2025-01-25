package com.wanna_wanna.server.dto;

import lombok.Data;
import java.util.Set;
import java.util.UUID;
import java.util.Date;

@Data
public class ListWithUsersDTO {
  private UUID id;
  private String name;
  private UUID shareId;
  private String type;
  private Date deadline;
  private Set<SimpleUserDTO> users;
  private Date createdAt;
  private Date updatedAt;
}
