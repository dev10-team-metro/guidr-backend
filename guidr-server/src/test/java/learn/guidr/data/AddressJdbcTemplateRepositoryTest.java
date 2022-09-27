package learn.guidr.data;

import learn.guidr.models.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        assertTrue(result.size() >= 5);

        Address address = new Address();
        address.setAddressId(1);
        address.setAddress("The Ridge");
        address.setZipCode(05263);
        address.setCity("Chicago");
        address.setState("IL");


        assertTrue(result.contains(address));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}