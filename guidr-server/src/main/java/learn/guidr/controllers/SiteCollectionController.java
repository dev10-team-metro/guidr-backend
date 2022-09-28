//package learn.guidr.controllers;
//
//import learn.guidr.domain.ResultType;
//import learn.guidr.models.SiteCollection;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/guidr/collection")
//public class SiteCollectionController {
//
//    private final SitecollectionService service;
//
//    public SiteCollectionController(SitecollectionService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<SiteCollection> findAll() {
//        return service.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public List<SiteCollection> findById(@PathVariable int id) {
//        return service.findbyId(id);
//    }
//
//    // TODO: update params with ones used in data/domain layers
//    @GetMapping("/{state}/{city}")
//    public List<SiteCollection> findByCity(@PathVariable String state, @PathVariable String city) {
//        return service.findByCity(city, state);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody SiteCollection siteCollection) {
//        SiteCollectionResult result = service.create(siteCollection);
//
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable int id, @RequestBody SiteCollection siteCollection) {
//        if (id != siteCollection.getCollectionId) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        SiteCollectionResult result = service.update(siteCollection);
//        if (!result.isSuccess()) {
//            if (result.getResultType() == ResultType.NOT_FOUND) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable int id) {
//        SiteCollectionResult result = service.deleteById(id);
//
//        if (result.getResultType() == ResultType.NOT_FOUND) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
