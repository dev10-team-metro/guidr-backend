package learn.guidr.data;

import learn.guidr.models.Landmark;
import learn.guidr.models.SiteCollection;

import java.util.List;

public interface LandmarkRepository {
    List<Landmark> findAll() throws DataAccessException;

    Landmark findById(int id) throws DataAccessException;

    Landmark create(Landmark landmark) throws DataAccessException;

    boolean update(Landmark landmark) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
