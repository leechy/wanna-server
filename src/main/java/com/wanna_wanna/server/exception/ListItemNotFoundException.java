package com.wanna_wanna.server.exception;

public class ListItemNotFoundException extends RuntimeException {
  public ListItemNotFoundException(String message) {
    super(message);
  }
}
