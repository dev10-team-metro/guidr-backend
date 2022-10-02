package learn.guidr.data;

import learn.guidr.models.Address;
import learn.guidr.models.Landmark;
import learn.guidr.models.Review;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewJdbcTemplateRepositoryTest {

    @Autowired
    private ReviewJdbcTemplateRepository repository;

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
    void findAll() throws DataAccessException {
        List<Review> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 2);

        Review review = new Review();
        review.setReviewId(1);
        review.setDescription("Great tour!");
        review.setRating(BigDecimal.valueOf(5));
        review.setCollectionId(1);
        review.setUserId(1);

        assertTrue(result.contains(review));
    }

    @Test
    void create() throws DataAccessException {
        Review review = new Review();
        review.setDescription("Test Review");
        review.setRating(BigDecimal.valueOf(3));
        review.setCollectionId(1);
        review.setUserId(2);

        Review result = repository.create(review);

        assertNotNull(result);
        assertEquals(3, result.getReviewId());
    }

    @Test
    void update() throws DataAccessException {
        Review review = new Review();
        review.setReviewId(2);
        review.setDescription("Test review description");
        review.setRating(BigDecimal.valueOf(4));
        review.setCollectionId(1);
        review.setUserId(1);

        assertTrue(repository.update(review));
    }

    @Test
    void deleteById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
    }
}