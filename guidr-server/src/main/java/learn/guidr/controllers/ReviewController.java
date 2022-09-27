package learn.guidr.controllers;

import learn.guidr.models.Review;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidr/review")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Review review) {
        ReviewResult result = service.create(review);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Review review) {
        if (id != review.getReviewId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        ReviewResult result = service.update(review);
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
        ReviewResult result = service.deleteById(id);

        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
