package com.wanna_wanna.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "items")
@Data
public class WItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "item_id", updatable = false, nullable = false)
  private UUID id;

  private String name;
  private String units;

  @Column(name = "type")
  private String type = "task";

  @Column(name = "is_active")
  private boolean isActive = true;

  @Column(name = "is_public")
  private boolean isPublic = false;

  private boolean deleted = false;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;
}
