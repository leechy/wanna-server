package com.wanna_wanna.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

  private final ErrorAttributes errorAttributes;

  public CustomErrorController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @RequestMapping("/error")
  public ResponseEntity<Map<String, Object>> handleError(WebRequest webRequest) {
    Map<String, Object> attributes = errorAttributes.getErrorAttributes(
        webRequest,
        ErrorAttributeOptions.defaults());

    HttpStatus status = HttpStatus.valueOf((Integer) attributes.get("status"));
    return new ResponseEntity<>(attributes, status);
  }
}
