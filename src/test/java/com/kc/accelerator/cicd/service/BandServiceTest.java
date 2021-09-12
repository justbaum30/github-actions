package com.kc.accelerator.cicd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.kc.accelerator.cicd.exception.BandNotFoundException;
import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.repository.BandRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
  void getAllBands_ReturnsBands() {
    final List<Band> expectedBands = Collections.singletonList(buildBand());
    when(mockBandRepository.findAll()).thenReturn(expectedBands);

    assertThat(service.getAllBands()).isEqualTo(expectedBands);
  }

  @Test
  void getBand_BandFound_ReturnsBand() {
    final Band expectedBand = buildBand();
    when(mockBandRepository.findById("2112")).thenReturn(Optional.of(expectedBand));

    assertThat(service.getBand("2112")).isEqualTo(expectedBand);
  }

  @Test
  void getBand_BandNotFound_ThrowsException() {
    when(mockBandRepository.findById("2112")).thenReturn(Optional.empty());

    assertThrows(BandNotFoundException.class, () -> service.getBand("2112"));
  }

  @Test
  void createBand_SavesAndReturnsBand() {
    final Band expectedBand = buildBand();
    when(mockBandRepository.save(expectedBand)).thenReturn(expectedBand);

    assertThat(service.createBand(expectedBand)).isEqualTo(expectedBand);
  }

  @Test
  void getBestBand_ReturnsHighestRockLevel() {
    final Band expectedBand = buildBand();
    when(mockBandRepository.findTopByOrderByRockLevelDesc()).thenReturn(expectedBand);

    assertThat(service.getBestBand()).isEqualTo(expectedBand);
  }

  private Band buildBand() {
    return Band.builder()
        .id("2112")
        .name("Rush")
        .rockLevel(10)
        .members(Arrays.asList("Geddy Lee", "Alex Lifeson", "Neil Peart"))
        .build();
  }
}
