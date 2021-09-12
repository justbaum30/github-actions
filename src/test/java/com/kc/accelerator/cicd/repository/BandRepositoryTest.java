package com.kc.accelerator.cicd.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kc.accelerator.cicd.model.Band;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BandRepositoryTest {

  @Autowired private BandRepository repository;

  @Test
  void findTopByOrderByRockLevelDesc_ReturnsRush() {
    Band foundBand = repository.findTopByOrderByRockLevelDesc();

    assertThat(foundBand).isNotNull();
    assertThat(foundBand.getId()).isEqualTo("2112");
  }
}
