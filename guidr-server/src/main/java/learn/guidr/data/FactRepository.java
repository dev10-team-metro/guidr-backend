package learn.guidr.data;

import learn.guidr.models.Fact;

import java.util.List;

public interface FactRepository {
    List<Fact> findAll() throws DataAccessException;

    List<Fact> findByLandmark(int landmarkId) throws DataAccessException;

    Fact create(Fact fact) throws DataAccessException;

    boolean update(Fact fact) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
