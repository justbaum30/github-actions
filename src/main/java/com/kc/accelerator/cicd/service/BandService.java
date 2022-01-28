package com.kc.accelerator.cicd.service;

import com.kc.accelerator.cicd.exception.BandNotFoundException;
import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.repository.BandRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class BandService {

  private final BandRepository bandRepository;

  public BandService(BandRepository bandRepository) {
    this.bandRepository = bandRepository;
  }

  public List<Band> getAllBands() {
    return StreamSupport.stream(bandRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public Band getBand(String bandId) {
    return bandRepository.findById(bandId).orElseThrow(BandNotFoundException::new);
  }

  public Band createBand(Band newBand) {
    return bandRepository.save(newBand);
  }

  public void deleteBand(String bandId) {
  
    bandRepository.deleteById(bandId);
  }

  public Band getBestBand() {
    return bandRepository.findTopByOrderByRockLevelDesc();
  }
}
