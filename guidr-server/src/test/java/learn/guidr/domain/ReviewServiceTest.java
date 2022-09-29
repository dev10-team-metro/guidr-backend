package learn.guidr.domain;

import learn.guidr.data.CollectionRepository;
import learn.guidr.data.DataAccessException;
import learn.guidr.data.ReviewRepository;
import learn.guidr.models.Review;
import learn.guidr.models.SiteCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReviewServiceTest {


    @Autowired
    ReviewService service;

    @MockBean
    ReviewRepository reviewRepository;

    @Test
    void shouldCreate() throws DataAccessException {
        Review review = new Review(0,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);
        Review mockOut = new Review(1,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);

        when(reviewRepository.create(review)).thenReturn(mockOut);

        Result<Review> actual = service.create(review);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreate() throws DataAccessException {
        Review review = new Review(1,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);
        Review mockOut = new Review(2,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);

        when(reviewRepository.create(review)).thenReturn(mockOut);

        Result<Review> actual = service.create(review);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Review review = new Review(1,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);

        when(reviewRepository.update(review)).thenReturn(true);
        Result<Review> actual = service.update(review);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Review review = new Review(35,"TEST DESCRIPTION", new BigDecimal(5), 1, 2);

        when(reviewRepository.update(review)).thenReturn(false);
        Result<Review> actual = service.update(review);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() throws DataAccessException {
        Review review = new Review(0,null, new BigDecimal(5), 1, 2);

        Result<Review> actual = service.update(review);
        assertEquals(ResultType.INVALID, actual.getType());

    }
}
