package learn.guidr.controllers;

import learn.guidr.domain.LandmarkService;
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
    public List<Landmark> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public List<Landmark> findById(@PathVariable int id) {
        return service.findbyId(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Landmark landmark) {
        LandmarkResult result = service.create(landmark);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Landmark landmark) {
        if (id != landmark.getLandmarkId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        LandmarkResult result = service.update(landmark);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        LandmarkResult result = service.deleteById(id);

        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
