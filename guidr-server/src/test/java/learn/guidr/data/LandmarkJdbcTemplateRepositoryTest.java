package learn.guidr.data;

import learn.guidr.models.Address;
import learn.guidr.models.Fact;
import learn.guidr.models.Landmark;
import learn.guidr.models.SiteCollection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LandmarkJdbcTemplateRepositoryTest {

    @Autowired
    private LandmarkJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Landmark> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 4);

        Landmark landmark = new Landmark();
        landmark.setLandmarkId(1);
        landmark.setName("Stone Street Historic District");
        landmark.setPrice(BigDecimal.valueOf(0));
        Address address = new Address(1,"Stone St", "NYC", "NY", 10004);
        landmark.setAddress(address);
        landmark.setCollectionId(1);

        assertTrue(result.contains(landmark));
    }

    @Test
    void shouldFindById() throws DataAccessException {
        Landmark result = repository.findById(2);
        assertEquals("US Custom House", result.getName());
        assertNotNull(result);
    }

    @Test
    void create() throws DataAccessException {
        Landmark landmark = new Landmark();
        landmark.setName("Test Landmark");
        landmark.setPrice(BigDecimal.valueOf(0));
        Address address = new Address(1,"Stone St", "NYC", "NY", 10004);
        landmark.setAddress(address);
        landmark.setCollectionId(1);

        Landmark result = repository.create(landmark);

        assertNotNull(result);
        assertEquals(5, result.getLandmarkId());

        assertEquals(result, repository.findById(5));
    }

    @Test
    void update() throws DataAccessException {
        Landmark landmark = new Landmark();
        landmark.setLandmarkId(2);
        landmark.setName("Update Test");
        landmark.setPrice(BigDecimal.valueOf(0));
        Address address = new Address(2,"1 Bowling Green", "NYC", "NY", 10004);
        landmark.setAddress(address);
        landmark.setCollectionId(1);
        List<Fact> fact = new ArrayList<>();
        fact.add(new Fact(2, "US Custom House description fun fact", 2));
        landmark.setFacts(fact);

        assertTrue(repository.update(landmark));
        assertEquals(landmark, repository.findById(2));
    }

    @Test
    void deleteById() throws DataAccessException {
        assertTrue(repository.deleteById(3));
    }
}