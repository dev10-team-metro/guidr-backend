package learn.guidr.domain;

import learn.guidr.data.DataAccessException;
import learn.guidr.data.LandmarkRepository;
import learn.guidr.models.Address;
import learn.guidr.models.Landmark;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LandmarkServiceTest {

    @Autowired
    LandmarkService service;

    @MockBean
    LandmarkRepository landmarkRepository;

    Address testAddress = new Address(0,"TEST ADDRESS", "TEST CITY", "AB", 12345);

    @Test
    void shouldCreate() throws DataAccessException {
        Landmark landmark = new Landmark(0,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);
        Landmark mockOut = new Landmark(1,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);

        when(landmarkRepository.create(landmark)).thenReturn(mockOut);

        Result<Landmark> actual = service.create(landmark);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreate() throws DataAccessException {
        Landmark landmark = new Landmark(1,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);
        Landmark mockOut = new Landmark(2,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);

        when(landmarkRepository.create(landmark)).thenReturn(mockOut);

        Result<Landmark> actual = service.create(landmark);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Landmark landmark = new Landmark(1,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);

        when(landmarkRepository.update(landmark)).thenReturn(true);
        Result<Landmark> actual = service.update(landmark);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Landmark landmark = new Landmark(35,"TEST NAME", new BigDecimal("9.99"), testAddress, 1);

        when(landmarkRepository.update(landmark)).thenReturn(false);
        Result<Landmark> actual = service.update(landmark);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() throws DataAccessException {
        Landmark landmark = new Landmark(35,null, new BigDecimal("9.99"), testAddress, 1);

        Result<Landmark> actual = service.update(landmark);
        assertEquals(ResultType.INVALID, actual.getType());


        landmark.setLandmarkId(0);
        landmark.setAddress(testAddress);
        actual = service.update(landmark);
        assertEquals(ResultType.INVALID, actual.getType());
    }


}
