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
    }
}
