package learn.guidr.controllers;

import learn.guidr.data.DataAccessException;
import learn.guidr.domain.AddressService;
import learn.guidr.domain.Result;
import learn.guidr.domain.ResultType;
import learn.guidr.models.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidr/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<Address> findAll() throws DataAccessException {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Address address) throws DataAccessException {
        Result<Address> result = service.create(address);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Address address) throws DataAccessException {
        if (id != address.getAddressId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Address> result = service.update(address);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DataAccessException {
        Result<Address> result = service.deleteById(id);

        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
