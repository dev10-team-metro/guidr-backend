package learn.guidr.data;

import learn.guidr.data.mappers.AddressMapper;
import learn.guidr.models.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> origin

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

<<<<<<< HEAD
=======
@Repository
>>>>>>> origin
public class AddressJdbcTemplateRepository implements AddressRepository{
    private final JdbcTemplate jdbcTemplate;

    public AddressJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> findAll() throws DataAccessException {

        final String sql = "select address_id, address, zip_code, city, state " +
                "from Address;";

        return jdbcTemplate.query(sql, new AddressMapper());
    }

    @Override
    public Address create(Address address) throws DataAccessException {

<<<<<<< HEAD
        final String sql = "insert into Reviews (address, zip_code, city, state) " +
=======
        final String sql = "insert into Address (address, zip_code, city, state) " +
>>>>>>> origin
                "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getAddress());
            statement.setInt(2, address.getZipCode());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getState());

            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        address.setAddressId(keyHolder.getKey().intValue());

        return address;
    }

    @Override
    public boolean update(Address address) throws DataAccessException {
        final String sql = "update Address set " +
                "address = ?, " +
                "zip_code = ?, " +
                "city = ?, " +
                "state = ?, " +
                "where address_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
<<<<<<< HEAD
                address.getAddressId(),
                address.getAddress(),
                address.getZipCode(),
                address.getCity(),
                address.getState());
=======
                address.getAddress(),
                address.getZipCode(),
                address.getCity(),
                address.getState(),
                address.getAddressId());
>>>>>>> origin

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Address where address_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
