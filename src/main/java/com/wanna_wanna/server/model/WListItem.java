package com.wanna_wanna.server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "list_items")
@Data
public class WListItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "list_items_id")
  private UUID id;

  private String name;
  private String type = "task";
  private String units;
  private Float quantity = 1.0f;

  @Column(name = "deadline")
  private Date deadline;

  @Column(name = "sort_order")
  private Integer sortOrder;

  private Integer ongoing = 0;
  private UUID assignee;
  private boolean completed = false;
  private boolean deleted = false;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "list_id")
  private WList list;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private WItem item;
}
