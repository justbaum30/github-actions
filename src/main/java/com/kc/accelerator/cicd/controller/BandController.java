package com.kc.accelerator.cicd.controller;

import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.service.BandService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class BandController {

  private final BandService bandService;

  public BandController(final BandService bandService) {
    this.bandService = bandService;
  }

  @GetMapping("/api/v1/bands")
  public List<Band> getAllBands() {
    return bandService.getAllBands();
  }

  @GetMapping("/api/v1/bands/{bandId}")
  public Band getBand(@PathVariable String bandId) {
    return bandService.getBand(bandId);
  }

  @PostMapping("/api/v1/bands")
  public Band createBand(@RequestBody @Valid Band newBand) {
    return bandService.createBand(newBand);
  }

  @DeleteMapping("/api/v1/bands/{bandId}")
  public void deleteBand(@PathVariable String bandId) {
      bandService.deleteBand(bandId);
  }

  @GetMapping("/api/v1/bands/best")
  public Band getBestBand() {
    return bandService.getBestBand();
  }
}
