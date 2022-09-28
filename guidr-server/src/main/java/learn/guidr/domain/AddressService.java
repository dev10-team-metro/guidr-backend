package learn.guidr.domain;

import learn.guidr.data.AddressRepository;
import learn.guidr.data.DataAccessException;
import learn.guidr.models.Address;
import learn.guidr.models.Landmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public Result<Address> create(Address address) throws DataAccessException {
        Result<Address> result = validate(address);

        if (!result.isSuccess()) {
            return result;
        }

        if (address.getAddressId() != 0) {
            result.addMessage("Address cannot be set", ResultType.INVALID);
            return result;
        }

        address = repository.create(address);
        result.setPayload(address);
        return result;

    }

    public Result<Address> update(Address address) throws DataAccessException {
        Result<Address> result = validate(address);

        if (!result.isSuccess()) {
            return result;
        }

        if (address.getAddressId() <= 0) {
            result.addMessage("Address ID must be set in order to update", ResultType.INVALID);
        }

        if (!repository.update(address)) {
            result.addMessage("Address does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Address> validate(Address address) throws DataAccessException {
        Result<Address> result = new Result<>();

        if (address == null) {
            result.addMessage("Landmark cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(address.getAddress())) {
            result.addMessage("An address name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(address.getCity())) {
            result.addMessage("A city name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(address.getState())) {
            result.addMessage("A state name is required", ResultType.INVALID);
        }

        if (address.getZipCode() == 0) {
            result.addMessage("A zipcode name is required", ResultType.INVALID);
        }

        if (isDuplicateAddress(address) && isDuplicateZipCode(address)) {
            result.addMessage("Cannot have a duplicate address", ResultType.INVALID);
        }

        return result;
    }

    private boolean isDuplicateAddress(Address address) throws DataAccessException {
        return findAll().stream()
                .anyMatch(address1 -> address1.getAddress().equals(address.getAddress()));
    }

    private boolean isDuplicateZipCode(Address address) throws DataAccessException {
        return findAll().stream()
                .anyMatch(address1 -> address1.getZipCode() == (address.getZipCode()));
    }
    

    private Result<Address> validateReference(int addressId) {
        Result<Address> result = new Result<>();





        return result;

        //todo delete validation


    }
}

