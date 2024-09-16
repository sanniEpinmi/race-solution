package com.test.race;

import com.test.race.model.Race;
import com.test.race.model.RaceResult;
import com.test.race.model.Rider;
import com.test.race.repository.RaceRepository;
import com.test.race.repository.RaceResultRepository;
import com.test.race.repository.RiderRepository;
import com.test.race.services.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(RaceService.class) // Import the service we are testing
public class RaceServiceTest {

    @Autowired
    private RaceService raceService;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private RaceResultRepository raceResultRepository;

    private Race race;

    @BeforeEach
    public void setUp() {
        // Create Riders
        Rider rider1 = new Rider();
        rider1.setName("Rider One");
        Rider rider2 = new Rider();
        rider2.setName("Rider Two");
        Rider rider3 = new Rider();
        rider3.setName("Rider Three");
        Rider rider4 = new Rider();
        rider4.setName("Rider Four");
        Rider rider5 = new Rider();
        rider5.setName("Rider Five");

        riderRepository.save(rider1);
        riderRepository.save(rider2);
        riderRepository.save(rider3);
        riderRepository.save(rider4);
        riderRepository.save(rider5);

        // Create Race
        race = new Race();
        race.setLocation("Mountain Trail");
        race.setRaceDateTime(LocalDateTime.now());
        raceRepository.save(race);

        // Create Race Results
        RaceResult result1 = new RaceResult(1L,rider1, race, 3600L, true); // 1 hour
        RaceResult result2 = new RaceResult(2L,rider2, race, 4200L, true); // 1 hour 10 mins
        RaceResult result3 = new RaceResult(3L,rider3, race, 3000L, true); // 50 mins
        RaceResult result4 = new RaceResult(4L,rider4, race, null, false); // Did not finish

        raceResultRepository.save(result1);
        raceResultRepository.save(result2);
        raceResultRepository.save(result3);
        raceResultRepository.save(result4);
    }

    @Test
    public void testGetFastestRiders() {
        List<Rider> fastestRiders = raceService.getFastestRiders(race.getId());
        assertEquals(3, fastestRiders.size());
        assertEquals("Rider Three", fastestRiders.get(0).getName()); // Rider with 50 mins
        assertEquals("Rider One", fastestRiders.get(1).getName()); // Rider with 1 hour
        assertEquals("Rider Two", fastestRiders.get(2).getName()); // Rider with 1 hour 10 mins
    }

    @Test
    public void testGetRidersThatDidNotFinish() {
        List<Rider> notFinishedRiders = raceService.getRidersThatDidNotFinish(race.getId());
        assertEquals(1, notFinishedRiders.size());
        assertEquals("Rider Four", notFinishedRiders.get(0).getName());
    }

    @Test
    public void testGetRidersThatDidNotTakePart() {
        List<Rider> notParticipatedRiders = raceService.getRidersThatDidNotTakePart(race.getId());
        assertEquals(1, notParticipatedRiders.size());
        assertEquals("Rider Five", notParticipatedRiders.get(0).getName()); // Rider who didn't take part
    }
}