package learn.guidr.data;

import learn.guidr.models.Address;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AddressJdbcTemplateRepositoryTest {


    @Autowired
    private AddressJdbcTemplateRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Address> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 4);

        Address address = new Address();
        address.setAddressId(1);
        address.setAddress("Stone St");
        address.setZipCode(10004);
        address.setCity("NYC");
        address.setState("NY");

        assertTrue(result.contains(address));
    }

    @Test
    void shouldCreate() throws DataAccessException {
        Address address = new Address();
        address.setAddress("591 Morgan Ave");
        address.setZipCode(10004);
        address.setCity("NYC");
        address.setState("NY");

        Address result = repository.create(address);

        assertNotNull(result);
        assertEquals(5, result.getAddressId());

    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Address address = new Address();
        address.setAddressId(2);
        address.setAddress("2 Bowling Green");
        address.setZipCode(10005);
        address.setCity("Chicago");
        address.setState("IL");

        assertTrue(repository.update(address));
    }

    @Test
    void deleteById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
    }
}