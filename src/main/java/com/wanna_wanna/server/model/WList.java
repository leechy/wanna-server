package com.wanna_wanna.server.model;

import java.util.UUID;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lists")
public class WList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "list_id")
  private UUID id;

  @Column(name = "share_id")
  private UUID shareId;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "deadline")
  private Date deadline;

  @Column(name = "completed")
  private boolean completed = false;

  @Column(name = "completed_at")
  private Date completedAt;

  @Column(name = "notify_on_list_shared")
  private boolean notifyOnListShared = true;

  @Column(name = "notify_on_list_items_update")
  private boolean notifyOnListItemsUpdate = true;

  @Column(name = "notify_on_item_state_update")
  private boolean notifyOnItemStateUpdate = true;

  @Column(name = "deleted")
  private boolean deleted = false;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  // Constructors
  public WList() {
  }

  public WList(
      String name,
      String type,
      Date deadline,
      boolean completed,
      Date completedAt,
      boolean notifyOnListShared,
      boolean notifyOnListItemsUpdate,
      boolean notifyOnItemStateUpdate) {
    this.name = name;
    this.type = type;
    this.deadline = deadline;
    this.completed = completed;
    this.completedAt = completedAt;
    this.notifyOnListShared = notifyOnListShared;
    this.notifyOnListItemsUpdate = notifyOnListItemsUpdate;
    this.notifyOnItemStateUpdate = notifyOnItemStateUpdate;
    this.deleted = false;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public UUID getShareId() {
    return shareId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public Date getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(Date completedAt) {
    this.completedAt = completedAt;
  }

  public boolean isNotifyOnListShared() {
    return notifyOnListShared;
  }

  public void setNotifyOnListShared(boolean notifyOnListShared) {
    this.notifyOnListShared = notifyOnListShared;
  }

  public boolean isNotifyOnListItemsUpdate() {
    return notifyOnListItemsUpdate;
  }

  public void setNotifyOnListItemsUpdate(boolean notifyOnListItemsUpdate) {
    this.notifyOnListItemsUpdate = notifyOnListItemsUpdate;
  }

  public boolean isNotifyOnItemStateUpdate() {
    return notifyOnItemStateUpdate;
  }

  public void setNotifyOnItemStateUpdate(boolean notifyOnItemStateUpdate) {
    this.notifyOnItemStateUpdate = notifyOnItemStateUpdate;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  // toString
  @Override
  public String toString() {
    return "List{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", shareId=" + shareId +
        ", type='" + type + '\'' +
        ", deadline=" + deadline +
        ", completedAt=" + completedAt +
        ", notifyOnListShared=" + notifyOnListShared +
        ", notifyOnListItemsUpdate=" + notifyOnListItemsUpdate +
        ", notifyOnItemStateUpdate=" + notifyOnItemStateUpdate +
        ", deleted=" + deleted +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
