package com.test.race.services;

import com.test.race.model.Race;
import com.test.race.model.RaceResult;
import com.test.race.model.Rider;
import com.test.race.repository.RaceRepository;
import com.test.race.repository.RaceResultRepository;
import com.test.race.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

        @Autowired
        private RaceRepository raceRepository;

        @Autowired
        private RiderRepository riderRepository;

        @Autowired
        private RaceResultRepository raceResultRepository;

        public List<Rider> getFastestRiders(Long raceId) {
            List<RaceResult> results = raceResultRepository.findByRaceIdOrderByTimeTakenAsc(raceId);
            return results.stream().filter(RaceResult::isFinished).limit(3)
                    .map(RaceResult::getRider).collect(Collectors.toList());
        }

    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }


    public List<RaceResult> getAllRaces() {
        return raceResultRepository.findAll();
    }

        public List<Rider> getRidersThatDidNotFinish(Long raceId) {
            return raceResultRepository.findByRaceIdAndFinishedFalse(raceId)
                    .stream().map(RaceResult::getRider).collect(Collectors.toList());
        }

        public List<Rider> getRidersThatDidNotTakePart(Long raceId) {
            List<Rider> allRiders = riderRepository.findAll();
            List<Rider> participants = raceResultRepository.findByRaceIdOrderByTimeTakenAsc(raceId)
                    .stream().map(RaceResult::getRider).collect(Collectors.toList());
            return allRiders.stream().filter(rider -> !participants.contains(rider)).collect(Collectors.toList());
        }

    public Rider createRider(Rider rider) {
            rider.setDateCreated(LocalDateTime.now());
        riderRepository.save(rider);
        return rider;
    }

    public Race createRace(Race race) {
            race.setRaceDateTime(LocalDateTime.now());
            race.setDateCreated(LocalDateTime.now());
          raceRepository.save(race);
            return race;
        }


    public RaceResult createRaceResult(RaceResult raceResult) {
        raceResultRepository.save(raceResult);
        return raceResult;
    }

    }
