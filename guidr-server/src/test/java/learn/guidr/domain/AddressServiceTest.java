//package learn.guidr.domain;
//
//import learn.guidr.data.AddressRepository;
//import learn.guidr.data.DataAccessException;
//import learn.guidr.models.Address;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class AddressServiceTest {
//
//    @Autowired
//    AddressService service;
//
//    @Autowired
//    AddressRepository addressRepository;
//
//    @Test
//    void shouldCreate() throws DataAccessException {
//        Address address = new Address(0,"TEST ADDRESS", "TEST CITY", "TEST STATE", 12345);
//        Address mockOut = new Address(1,"TEST ADDRESS", "TEST CITY", "TEST STATE", 12345);
//
//        when(addressRepository.create(address)).thenReturn(mockOut);
//
//        Result<Address> actual = service.create(address);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//        assertEquals(mockOut, actual.getPayload());
//    }
//}
