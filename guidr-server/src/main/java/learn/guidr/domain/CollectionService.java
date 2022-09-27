package learn.guidr.domain;

import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    private final CollectionRepository repository;

    public CollectionService(CollectionRepository repository){
        this.repository = repository;
    }

}
