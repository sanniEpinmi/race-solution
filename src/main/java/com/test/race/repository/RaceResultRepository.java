package com.test.race.repository;

import com.test.race.model.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    List<RaceResult> findByRaceIdOrderByTimeTakenAsc(Long raceId);
    List<RaceResult> findByRaceIdAndFinishedFalse(Long raceId);
}
