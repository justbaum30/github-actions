package com.kc.accelerator.cicd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.accelerator.cicd.model.Band;
import com.kc.accelerator.cicd.service.BandService;
import java.util.Arrays;
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
  void getBestBand_ReturnsBestBand() throws Exception {
    final Band rushBand =
        Band.builder()
            .id("2112")
            .name("Rush")
            .rockLevel(10)
            .members(Arrays.asList("Geddy Lee", "Alex Lifeson", "Neil Peart"))
            .build();
    when(mockBandService.getBestBand()).thenReturn(rushBand);

    mockMvc
        .perform(get("/api/v1/bands/best").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(rushBand)));
  }
}
