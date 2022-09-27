package learn.guidr.data;

import learn.guidr.models.Address;
import learn.guidr.models.Review;

import java.util.List;

public interface AddressRepository {
    List<Address> findAll() throws DataAccessException;

    Address create(Address address) throws DataAccessException;

    boolean update(Address address) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
