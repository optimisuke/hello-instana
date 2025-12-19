package com.example.otel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HealthController {

  private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(
    HealthController.class.getName()
  );

  @GetMapping("/health")
  ResponseEntity<String> health() {
    log.info("health endpoint called");
    return ResponseEntity.ok("ok");
  }
}
