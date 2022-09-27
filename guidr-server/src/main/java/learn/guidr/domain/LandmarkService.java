package learn.guidr.domain;

import learn.guidr.data.DataAccessException;
import learn.guidr.data.LandmarkRepository;
import learn.guidr.models.Landmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandmarkService {
    private final LandmarkRepository repository;

    public LandmarkService(LandmarkRepository repository){
        this.repository = repository;
    }

    public List<Landmark> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Landmark findById(int landmarkId) throws DataAccessException {
        return repository.findById(landmarkId);
    }

    public Result<Landmark> create(Landmark landmark) throws DataAccessException {

        Result<Landmark> result = validate(landmark);

        if(!result.isSuccess()){
            return result;
        }

        if(landmark.getLandmarkId() != 0){
            result.addMessage("Landmark cannot be set", ResultType.INVALID);
        }

        landmark = repository.create(landmark);
        result.setPayload(landmark);
        return result;
    }

    public Result<Landmark> update(Landmark landmark) throws DataAccessException {

        Result<Landmark> result = validate(landmark);

        if(!result.isSuccess()){
            return result;
        }

        if(landmark.getLandmarkId() <= 0){
            result.addMessage("Landmark ID must be set in order to update a landmark", ResultType.INVALID);
        }

        if(!repository.update(landmark)){
            result.addMessage("Landmark does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Landmark> deleteById(int landmarkId) throws DataAccessException {
        Result<Landmark> result = new Result<>();

        if(!repository.deleteById(landmarkId)){
            result.addMessage("Landmark does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Landmark> validate(Landmark landmark) throws DataAccessException {
        Result<Landmark> result = new Result<>();

        if(landmark == null){
            result.addMessage("Landmark cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(landmark.getName())){
            result.addMessage("A landmark name is required", ResultType.INVALID);
        }

        if(isDuplicate(landmark)){
            result.addMessage("Cannot have a duplicate landmark", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicate(Landmark landmark) throws DataAccessException {
        return findAll().stream()
                .anyMatch(landmark1 -> landmark1.getName().equals(landmark.getName()));
    }
}
