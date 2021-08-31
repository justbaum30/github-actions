package com.kc.accelerator.cicd.service;

import com.kc.accelerator.cicd.model.Band;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BandService {

  public Band getBestBand() {
    return Band.builder()
        .name("Rush")
        .members(Arrays.asList("Geddy Lee", "Alex Lifeson", "Neil Peart"))
        .build();
  }
}
