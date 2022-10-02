package learn.guidr.domain;

import learn.guidr.data.DataAccessException;
import learn.guidr.data.FactRepository;
import learn.guidr.models.Fact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FactServiceTest {

    @Autowired
    FactService service;

    @MockBean
    FactRepository factRepository;

    @Test
    void shouldCreate() throws DataAccessException {
        Fact fact = new Fact(0,"TEST DESCRIPTION", 1);
        Fact mockOut = new Fact(1,"TEST DESCRIPTION", 1);

        when(factRepository.create(fact)).thenReturn(mockOut);

        Result<Fact> actual = service.create(fact);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreate() throws DataAccessException {
        Fact fact = new Fact(1,"TEST DESCRIPTION", 1);
        Fact mockOut = new Fact(2,"TEST DESCRIPTION", 1);

        when(factRepository.create(fact)).thenReturn(mockOut);

        Result<Fact> actual = service.create(fact);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Fact fact = new Fact(1,"TEST DESCRIPTION", 1);

        when(factRepository.update(fact)).thenReturn(true);
        Result<Fact> actual = service.update(fact);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Fact fact = new Fact(35,"TEST DESCRIPTION", 1);

        when(factRepository.update(fact)).thenReturn(false);
        Result<Fact> actual = service.update(fact);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() throws DataAccessException {
        Fact fact = new Fact(35,null, 1);

        Result<Fact> actual = service.update(fact);
        assertEquals(ResultType.INVALID, actual.getType());


        fact.setLandmarkId(0);
        fact.setDescription("TEST DESCRIPTION");
        actual = service.update(fact);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
