package learn.guidr.domain;

import learn.guidr.data.DataAccessException;
import learn.guidr.data.ReviewRepository;
import learn.guidr.models.Landmark;
import learn.guidr.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository){
        this.repository = repository;
    }


    public List<Review> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Result<Review> create(Review review) throws DataAccessException {

        Result<Review> result = validate(review);

        if(!result.isSuccess()){
            return result;
        }

        if(review.getReviewId() != 0){
            result.addMessage("Review cannot be posted", ResultType.INVALID);
        }

        review = repository.create(review);
        result.setPayload(review);
        return result;
    }

    public Result<Review> update(Review review) throws DataAccessException{

        Result<Review> result = new Result<>();

        if(!result.isSuccess()){
            return result;
        }

        if(review.getReviewId() <= 0){
            result.addMessage("Review ID must be set in order to update", ResultType.INVALID);
            return result;
        }

        if(!repository.update(review)){
            result.addMessage("Review does not exist", ResultType.NOT_FOUND);
            return result;
        }

        return result;
    }

    public Result<Review> delete(int reviewId) throws DataAccessException {
        Result<Review> result = new Result<>();

        if(!repository.deleteById(reviewId)){
            result.addMessage("Review does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Review> validate(Review review) throws DataAccessException{
        Result<Review> result = new Result<>();

        if(review == null){
            result.addMessage("Review cannot be null", ResultType.INVALID);
            return result;
        }


        if(isDuplicate(review)){
            result.addMessage("Cannot have a duplicate landmark", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicate(Review review) throws DataAccessException {
        return findAll().stream()
                .anyMatch(review1 -> review1.getDescription().equals(review.getDescription()));
    }
}


