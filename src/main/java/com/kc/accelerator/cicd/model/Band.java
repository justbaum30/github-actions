package com.kc.accelerator.cicd.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Band {

  private String name;
  private List<String> members;
}
