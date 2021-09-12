package com.kc.accelerator.cicd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.repository.BandRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BandServiceTest {

  @Mock private BandRepository mockBandRepository;

  private BandService service;

  @BeforeEach
  void setUp() {
    service = new BandService(mockBandRepository);
  }

  @Test
  void getBestBand_ReturnsHighestRockLevel() {
    final Band expectedBand =
        Band.builder()
            .id("2112")
            .name("Rush")
            .rockLevel(10)
            .members(Arrays.asList("Geddy Lee", "Alex Lifeson", "Neil Peart"))
            .build();
    when(mockBandRepository.findTopByOrderByRockLevelDesc()).thenReturn(expectedBand);

    assertThat(service.getBestBand()).isEqualTo(expectedBand);
  }
}
