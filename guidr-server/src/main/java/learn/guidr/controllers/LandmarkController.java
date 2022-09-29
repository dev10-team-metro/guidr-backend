package learn.guidr.controllers;

import learn.guidr.data.DataAccessException;
import learn.guidr.domain.LandmarkService;
import learn.guidr.domain.Result;
import learn.guidr.domain.ResultType;
import learn.guidr.models.Landmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidr/landmark")
public class LandmarkController {
    private final LandmarkService service;

    public LandmarkController(LandmarkService service) {
        this.service = service;
    }

    @GetMapping
    public List<Landmark> findAll() throws DataAccessException {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Landmark findById(@PathVariable int id) throws DataAccessException {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Landmark landmark) throws DataAccessException {
        Result<Landmark> result = service.create(landmark);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Landmark landmark) throws DataAccessException {
        if (id != landmark.getLandmarkId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Landmark> result = service.update(landmark);
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
        Result<Landmark> result = service.deleteById(id);

        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

