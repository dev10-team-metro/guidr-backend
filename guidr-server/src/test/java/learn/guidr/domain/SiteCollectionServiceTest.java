package learn.guidr.domain;

import learn.guidr.data.AddressRepository;
import learn.guidr.data.CollectionRepository;
import learn.guidr.data.DataAccessException;
import learn.guidr.models.Address;
import learn.guidr.models.SiteCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SiteCollectionServiceTest {

    @Autowired
    SiteCollectionService service;

    @MockBean
    CollectionRepository collectionRepository;

    @Test
    void shouldCreate() throws DataAccessException {
        SiteCollection collection = new SiteCollection(0,"TEST NAME", "TEST DESCRIPTION");
        SiteCollection mockOut = new SiteCollection(1,"TEST NAME", "TEST DESCRIPTION");

        when(collectionRepository.create(collection)).thenReturn(mockOut);

        Result<SiteCollection> actual = service.create(collection);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreate() throws DataAccessException {
        SiteCollection collection = new SiteCollection(1,"TEST NAME", "TEST DESCRIPTION");
        SiteCollection mockOut = new SiteCollection(2,"TEST NAME", "TEST DESCRIPTION");

        when(collectionRepository.create(collection)).thenReturn(mockOut);

        Result<SiteCollection> actual = service.create(collection);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        SiteCollection collection = new SiteCollection(1,"TEST NAME", "TEST DESCRIPTION");

        when(collectionRepository.update(collection)).thenReturn(true);
        Result<SiteCollection> actual = service.update(collection);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        SiteCollection collection = new SiteCollection(35,"TEST NAME", "TEST DESCRIPTION");

        when(collectionRepository.update(collection)).thenReturn(false);
        Result<SiteCollection> actual = service.update(collection);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() throws DataAccessException {
        SiteCollection collection = new SiteCollection(35,null, "TEST DESCRIPTION");

        Result<SiteCollection> actual = service.update(collection);
        assertEquals(ResultType.INVALID, actual.getType());

        collection.setName("TEST");
        collection.setDescription(" ");


        collection.setCollectionId(0);
        collection.setDescription("TEST DESCRIPTION");
        actual = service.update(collection);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
