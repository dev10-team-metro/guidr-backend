package learn.guidr.controllers;

import learn.guidr.data.DataAccessException;
import learn.guidr.domain.FactService;
import learn.guidr.domain.Result;
import learn.guidr.domain.ResultType;
import learn.guidr.models.Fact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidr/fact")
public class FactController {
    private final FactService service;

    public FactController(FactService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fact> findAll() throws DataAccessException {
        return service.findAll();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Fact fact) throws DataAccessException {
        Result<Fact> result = service.create(fact);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Fact fact) throws DataAccessException {
        if (id != fact.getFactId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Fact> result = service.update(fact);
        if (!result.isSuccess()) {
            if (result.getType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DataAccessException {
        Result<Fact> result = service.deleteById(id);

        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
