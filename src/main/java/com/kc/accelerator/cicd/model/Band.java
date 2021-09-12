package com.kc.accelerator.cicd.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bands")
public class Band {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @NotBlank private String name;

  @Min(1)
  @Max(10)
  private Integer rockLevel;

  @ElementCollection
  @CollectionTable(name = "band_members", joinColumns = @JoinColumn(name = "band_id"))
  @NotEmpty
  private List<String> members;
}
