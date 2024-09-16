package com.test.race.controller;

import com.test.race.model.Race;
import com.test.race.model.RaceResult;
import com.test.race.model.Rider;
import com.test.race.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    @Autowired
    private RaceService raceService;

    // Fastest 3 riders
    @GetMapping("/{raceId}/fastest")
    public List<Rider> getFastestRiders(@PathVariable Long raceId) {
        return raceService.getFastestRiders(raceId);
    }

    // Riders that did not finish
    @GetMapping("/{raceId}/not-finished")
    public List<Rider> getRidersThatDidNotFinish(@PathVariable Long raceId) {
        return raceService.getRidersThatDidNotFinish(raceId);
    }

    // Riders that did not take part
    @GetMapping("/{raceId}/not-participated")
    public List<Rider> getRidersThatDidNotParticipate(@PathVariable Long raceId) {
        return raceService.getRidersThatDidNotTakePart(raceId);
    }


    // All Riders

    @GetMapping("/all-riders")
    public List<Rider> getFastestRiders() {
        return raceService.getAllRiders();
    }

    @GetMapping("/all-races")
    public List<RaceResult> getAllRaces(){
        return raceService.getAllRaces();
    }


    @PostMapping("/create-riders")
    public Rider createRider(@RequestBody Rider rider) {
        return raceService.createRider(rider);
    }


    @PostMapping("/create-race")
    public Race createRace(@RequestBody Race race) {
        return raceService.createRace(race);
    }



    @PostMapping("/create-race-result")
    public RaceResult createRaceResult(@RequestBody RaceResult raceResult) {
        return raceService.createRaceResult(raceResult);
    }

}