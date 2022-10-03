package learn.guidr.data;


import learn.guidr.models.SiteCollection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CollectionJdbcTemplateRepositoryTest {

    @Autowired
    private CollectionJdbcTemplateRepository repository;

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
        List<SiteCollection> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 1);

        SiteCollection collection = new SiteCollection();
        collection.setCollectionId(1);
        collection.setName("New York-Collection #1");
        collection.setDescription("Go on a tour of downtown New York!");

        assertTrue(result.contains(collection));
    }

    @Test
    void shouldFindById() throws DataAccessException {
        SiteCollection result = repository.findById(1);
        assertNotNull(result);
    }

    @Test
    void shouldFindByCity() throws DataAccessException {
        List<SiteCollection> result = repository.findByCity("NYC", "NY");
        assertNotNull(result);
        assertTrue(result.size() == 1 || result.size() == 2);
    }

    @Test
    void shouldCreate() throws DataAccessException {
        SiteCollection collection = new SiteCollection();
        collection.setName("New York-Collection #2");
        collection.setDescription("Go on another tour");

        SiteCollection result = repository.create(collection);

        assertNotNull(result);
        assertEquals(3, result.getCollectionId());

        assertEquals(result, repository.findById(3));
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        SiteCollection collection = new SiteCollection();
        collection.setCollectionId(2);
        collection.setName("Test Collection #1");
        collection.setDescription("Test description #1");

        assertTrue(repository.update(collection));
        assertEquals(collection, repository.findById(2));
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
    }
}