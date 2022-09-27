package learn.guidr.domain;

import learn.guidr.data.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository){
        this.repository = repository;
    }
}
