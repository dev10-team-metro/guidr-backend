package learn.guidr.data;

import learn.guidr.models.Landmark;
import learn.guidr.models.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findAll() throws DataAccessException;

    Review create(Review review) throws DataAccessException;

    boolean update(Review review) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
