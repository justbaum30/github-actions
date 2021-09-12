package com.kc.accelerator.cicd.service;

import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.repository.BandRepository;
import org.springframework.stereotype.Service;

@Service
public class BandService {

  private final BandRepository bandRepository;

  public BandService(BandRepository bandRepository) {
    this.bandRepository = bandRepository;
  }

  public Band getBestBand() {
    return bandRepository.findTopByOrderByRockLevelDesc();
  }
}
