package com.kc.accelerator.cicd.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kc.accelerator.cicd.model.Band;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BandServiceTest {

  private BandService service;

  @BeforeEach
  void setUp() {
    service = new BandService();
  }

  @Test
  void getBestBand_ReturnsRush() {
    final Band expectedBand =
        Band.builder()
            .name("Rush")
            .members(Arrays.asList("Geddy Lee", "Alex Lifeson", "Neil Peart"))
            .build();

    assertThat(service.getBestBand()).isEqualTo(expectedBand);
  }
}
