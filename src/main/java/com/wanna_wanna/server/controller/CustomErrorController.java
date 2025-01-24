package com.wanna_wanna.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

  public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
    Map<String, Object> response = new HashMap<>();
    HttpStatus status = getStatus(request);

    response.put("status", status.value());
    response.put("error", status.getReasonPhrase());
    response.put("message", "An error occurred");

    return new ResponseEntity<>(response, status);
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode != null) {
      try {
        return HttpStatus.valueOf(statusCode);
      } catch (Exception ex) {
        // Ignore
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
