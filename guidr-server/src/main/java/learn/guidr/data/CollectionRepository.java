package learn.guidr.data;

import learn.guidr.models.SiteCollection;

import java.util.Collection;
import java.util.List;

public interface CollectionRepository {

    List<SiteCollection> findAll() throws DataAccessException;

    SiteCollection findById(int id) throws DataAccessException;

<<<<<<< HEAD
    List<SiteCollection>findByCity(String city) throws DataAccessException;
=======
    List<SiteCollection>findByCity(String city, String state) throws DataAccessException;
>>>>>>> origin

    SiteCollection create(SiteCollection collection) throws DataAccessException;

    boolean update(SiteCollection collection) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;


}
