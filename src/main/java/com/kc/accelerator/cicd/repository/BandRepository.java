package com.kc.accelerator.cicd.repository;

import com.kc.accelerator.cicd.model.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<Band, String> {

  Band findTopByOrderByRockLevelDesc();
}
