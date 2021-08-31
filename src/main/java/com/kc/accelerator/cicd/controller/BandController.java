package com.kc.accelerator.cicd.controller;

import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.service.BandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandController {

  private final BandService bandService;

  public BandController(final BandService bandService) {
    this.bandService = bandService;
  }

  @GetMapping("/api/v1/bands/best")
  public Band getBestBand() {
    return bandService.getBestBand();
  }
}
