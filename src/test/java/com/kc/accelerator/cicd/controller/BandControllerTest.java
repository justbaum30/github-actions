package com.kc.accelerator.cicd.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.accelerator.cicd.exception.BandNotFoundException;
import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.service.BandService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BandController.class)
@ExtendWith(SpringExtension.class)
class BandControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private BandService mockBandService;

  @Test
  void getAllBands_ReturnsBands() throws Exception {
    final List<Band> bands = Collections.singletonList(buildBand());
    when(mockBandService.getAllBands()).thenReturn(bands);

    mockMvc
        .perform(get("/api/v1/bands").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(bands)));
  }

  @Test
  void getBand_BandFound_ReturnsBand() throws Exception {
    final Band band = buildBand();
    when(mockBandService.getBand("2112")).thenReturn(band);

    mockMvc
        .perform(get("/api/v1/bands/2112").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(band)));
  }

  @Test
  void getBand_BandNotFound_Returns404() throws Exception {
    when(mockBandService.getBand("2112")).thenThrow(BandNotFoundException.class);

    mockMvc
        .perform(get("/api/v1/bands/2112").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void createBand_ValidRequest_ReturnsCreatedBand() throws Exception {
    final Band band = buildBand();
    when(mockBandService.createBand(band)).thenReturn(band);

    mockMvc
        .perform(
            post("/api/v1/bands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(band)))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(band)));
  }

  @Test
  void createBand_InvalidRequest_Returns400() throws Exception {
    final Band band = buildBand();
    band.setRockLevel(11);

    mockMvc
        .perform(
            post("/api/v1/bands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(band)))
        .andExpect(status().isBadRequest());
    verifyNoInteractions(mockBandService);
  }

  @Test
  void getBestBand_ReturnsBestBand() throws Exception {
    final Band band = buildBand();
    when(mockBandService.getBestBand()).thenReturn(band);

    mockMvc
        .perform(get("/api/v1/bands/best").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(band)));
  }

  @Test
  void deleteBand_Returns200() throws Exception {
    mockMvc
            .perform(delete("/api/v1/bands/2112").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    verify(mockBandService).deleteBand("2112");
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
