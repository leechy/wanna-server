package com.wanna_wanna.server.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;
  private String names;
  private boolean notifyOnListShared = true;
  private boolean notifyOnListItemsUpdate = true;
  private boolean notifyOnItemStateUpdate = true;
  private String expoPushToken;
  private String devicePushToken;
  private boolean deleted = false;
  private Date createdAt;
  private Date updatedAt;

  // Constructors
  public User() {
  }

  public User(String names, boolean notifyOnListShared, boolean notifyOnListItemsUpdate,
      boolean notifyOnItemStateUpdate, String expoPushToken, String devicePushToken) {
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
  public Long getId() {
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
