package learn.guidr.domain;

import learn.guidr.data.CollectionRepository;
import learn.guidr.data.DataAccessException;
import learn.guidr.models.SiteCollection;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private final CollectionRepository repository;

    public CollectionService(CollectionRepository repository){
        this.repository = repository;
    }

    public List<SiteCollection> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public SiteCollection findById(int collectionId) throws DataAccessException {
        return repository.findById(collectionId);
    }

    public List<SiteCollection> findByCity(String city, String state) throws DataAccessException {
        return repository.findByCity(city, state);
    }

    public Result<SiteCollection> create(SiteCollection collection) throws DataAccessException {

        Result<SiteCollection> result = validate(collection);

        if(!result.isSuccess()){
            return result;
        }

        if(collection.getCollectionId() != 0){
            result.addMessage("Collection cannot be set", ResultType.INVALID);
        }

        collection = repository.create(collection);
        result.setPayload(collection);
        return result;
    }

    public Result<SiteCollection> update(SiteCollection collection) throws DataAccessException {

        Result<SiteCollection> result = validate(collection);

        if(!result.isSuccess()){
            return result;
        }

        if(collection.getCollectionId() <= 0){
            result.addMessage("Collection ID must be set in order to update a collection", ResultType.INVALID);
            return result;
        }

        if(!repository.update(collection)){
            result.addMessage("Collection does not exist", ResultType.NOT_FOUND);
            return result;
        }

        return result;
    }

    public Result<SiteCollection> deleteById(int collectionId) throws DataAccessException {
        Result<SiteCollection> result = new Result<>();

        if(!repository.deleteById(collectionId)){
            result.addMessage("Collection does not exist", ResultType.NOT_FOUND);
        }

        //todo validation

        return result;
    }

    private Result<SiteCollection> validate(SiteCollection collection) throws DataAccessException {
        Result<SiteCollection> result = new Result<>();

        if(collection == null){
            result.addMessage("Collection cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(collection.getName())){
            result.addMessage("A collection name is required", ResultType.INVALID);
        }

        if(isDuplicateName(collection) && isDuplicateLandmarks(collection)){
            result.addMessage("Cannot have a duplicate collection", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicateName(SiteCollection collection) throws DataAccessException {
        return findAll().stream()
                .anyMatch(collection1 -> collection1.getName().equals(collection.getName()));
    }

    private boolean isDuplicateLandmarks(SiteCollection collection) throws DataAccessException {
        return findAll().stream()
                .anyMatch(collection1 -> collection1.getLandmarks().equals(collection.getLandmarks()));
    }






}

