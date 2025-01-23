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
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "uid")
  private UUID uid;

  @Column(name = "names")
  private String names;

  @Column(name = "notify_on_list_shared")
  private boolean notifyOnListShared = true;

  @Column(name = "notify_on_list_items_update")
  private boolean notifyOnListItemsUpdate = true;

  @Column(name = "notify_on_item_state_update")
  private boolean notifyOnItemStateUpdate = true;

  @Column(name = "expo_push_token")
  private String expoPushToken;

  @Column(name = "device_push_token")
  private String devicePushToken;

  @Column(name = "deleted")
  private boolean deleted = false;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  // Constructors
  public User() {
  }

  public User(
      String names,
      boolean notifyOnListShared,
      boolean notifyOnListItemsUpdate,
      boolean notifyOnItemStateUpdate,
      String expoPushToken,
      String devicePushToken) {
    this.names = names;
    this.notifyOnListShared = notifyOnListShared;
    this.notifyOnListItemsUpdate = notifyOnListItemsUpdate;
    this.notifyOnItemStateUpdate = notifyOnItemStateUpdate;
    this.expoPushToken = expoPushToken;
    this.devicePushToken = devicePushToken;
    this.deleted = false;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  // Getters and Setters
  public UUID getId() {
    return uid;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
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

  public String getExpoPushToken() {
    return expoPushToken;
  }

  public void setExpoPushToken(String expoPushToken) {
    this.expoPushToken = expoPushToken;
  }

  public String getDevicePushToken() {
    return devicePushToken;
  }

  public void setDevicePushToken(String devicePushToken) {
    this.devicePushToken = devicePushToken;
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

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
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
    return "User{" +
        "id=" + uid +
        ", names='" + names + '\'' +
        ", notifyOnListShared=" + notifyOnListShared +
        ", notifyOnListItemsUpdate=" + notifyOnListItemsUpdate +
        ", notifyOnItemStateUpdate=" + notifyOnItemStateUpdate +
        ", expoPushToken='" + expoPushToken + '\'' +
        ", devicePushToken='" + devicePushToken + '\'' +
        ", deleted=" + deleted +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
