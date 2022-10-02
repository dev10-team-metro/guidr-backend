package learn.guidr.domain;

import learn.guidr.data.DataAccessException;
import learn.guidr.data.FactRepository;
import learn.guidr.models.Fact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactService {

    private final FactRepository repository;

    public FactService(FactRepository repository) {
        this.repository = repository;
    }


    public List<Fact> findAll() throws DataAccessException{
        return repository.findAll();
    }

    public List<Fact> findByLandmark(int landmarkId) throws DataAccessException{
        return repository.findByLandmark(landmarkId);
    }

    public Result<Fact> create(Fact fact) throws DataAccessException {
        Result<Fact> result = validate(fact);

        if(!result.isSuccess()){
            return result;
        }

        if(fact.getFactId() !=0){
            result.addMessage("Fact cannot be set", ResultType.INVALID);
        }

        fact = repository.create(fact);
        result.setPayload(fact);
        return result;
    }

    public Result<Fact> update(Fact fact) throws DataAccessException{
        Result<Fact> result = validate(fact);

        if(!result.isSuccess()){
            return result;
        }

        if(fact.getFactId() <= 0){
            result.addMessage("Fact ID must be set in order to update a fact", ResultType.INVALID);
            return result;
        }

        if(!repository.update(fact)){
            result.addMessage("Fact does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Fact> deleteById(int factId) throws DataAccessException{
        Result<Fact> result = new Result<>();

        if(!repository.deleteById(factId)){
            result.addMessage("Fact does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Fact> validate(Fact fact) throws DataAccessException {
        Result<Fact> result = new Result<>();

        if(fact == null){
            result.addMessage("Fact cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(fact.getDescription())){
            result.addMessage("A fact description is required", ResultType.INVALID);
        }

        if(isDuplicate(fact)){
            result.addMessage("Cannot have a duplicate fact", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicate(Fact fact) throws DataAccessException {
        return findAll().stream()
                .anyMatch(fact1 -> fact1.getDescription().equals(fact.getDescription()));
    }


}
