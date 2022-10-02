package learn.guidr.domain;

import learn.guidr.data.AddressRepository;
import learn.guidr.data.DataAccessException;
import learn.guidr.models.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    AddressService service;

    @MockBean
    AddressRepository addressRepository;

    @Test
    void shouldCreate() throws DataAccessException {
        Address address = new Address(0,"TEST ADDRESS", "TEST CITY", "AB", 12345);
        Address mockOut = new Address(1,"TEST ADDRESS", "TEST CITY", "AB", 12345);

        when(addressRepository.create(address)).thenReturn(mockOut);

        Result<Address> actual = service.create(address);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotCreate() throws DataAccessException {
        Address address = new Address(1,"TEST ADDRESS", "TEST CITY", "AB", 12345);
        Address mockOut = new Address(2,"TEST ADDRESS", "TEST CITY", "AB", 12345);

        when(addressRepository.create(address)).thenReturn(mockOut);

        Result<Address> actual = service.create(address);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Address address = new Address(1, "TEST ADDRESS", "TEST CITY", "AB", 12345);

        when(addressRepository.update(address)).thenReturn(true);
        Result<Address> actual = service.update(address);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        Address address = new Address(35, "TEST ADDRESS", "TEST CITY", "AB", 12345);

        when(addressRepository.update(address)).thenReturn(false);
        Result<Address> actual = service.update(address);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() throws DataAccessException {
        Address address = new Address(35, null, "TEST CITY", "AB", 12345);

        Result<Address> actual = service.update(address);
        assertEquals(ResultType.INVALID, actual.getType());

        address.setAddress("TEST");
        address.setCity(" ");
        address.setState("AB");
        address.setZipCode(12345);

        actual = service.update(address);
        assertEquals(ResultType.INVALID, actual.getType());

        address.setAddressId(0);
        address.setCity("TEST CITY");
        actual = service.update(address);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
