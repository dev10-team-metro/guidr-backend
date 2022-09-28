package learn.guidr.data;

import learn.guidr.models.Fact;
import learn.guidr.models.SiteCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FactJdbcTemplateRepositoryTest {

    @Autowired
    private FactJdbcTemplateRepository repository;

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
        List<Fact> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 1);

        Fact fact = new Fact();
        fact.setFactId(1);
        fact.setDescription("Stone Street Historic District description fun fact");
        fact.setLandmarkId(1);

        assertTrue(result.contains(fact));
    }

    @Test
    void shouldFindByLandmark() throws DataAccessException {
        List<Fact> result = repository.findByLandmark(1);
        assertNotNull(result);
        assertTrue(result.size() == 1 || result.size() == 2);
    }

    @Test
    void shouldCreate() throws DataAccessException {
        Fact fact = new Fact();
        fact.setDescription("Charging Bull description fun fact");
        fact.setLandmarkId(2);

        Fact result = repository.create(fact);

        assertNotNull(result);
        assertEquals(3, result.getFactId());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Fact fact = new Fact();
        fact.setFactId(2);
        fact.setDescription("Fun fact test description");
        fact.setLandmarkId(1);

        assertTrue(repository.update(fact));
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
    }
}