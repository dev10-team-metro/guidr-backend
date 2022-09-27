package learn.guidr.data.mappers;

import learn.guidr.models.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();

        address.setAddressId(rs.getInt("address_id"));
        address.setAddress(rs.getString("address"));
        address.setZipCode(rs.getInt("zip_code"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));

<<<<<<< HEAD


=======
>>>>>>> origin
        return address;
    }
}
