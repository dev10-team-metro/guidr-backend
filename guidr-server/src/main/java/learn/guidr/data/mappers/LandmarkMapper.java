package learn.guidr.data.mappers;

import learn.guidr.models.Landmark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandmarkMapper implements RowMapper<Landmark> {

    @Override
    public Landmark mapRow(ResultSet rs, int rowNum) throws SQLException {
        Landmark landmark = new Landmark();

        landmark.setLandmarkId(rs.getInt("landmark_id"));
        landmark.setName(rs.getString("name"));
        landmark.setPrice(rs.getBigDecimal("price"));

        //address mapper and sitecollection mapper

        AddressMapper addressMapper = new AddressMapper();
        landmark.setAddress(addressMapper.mapRow(rs, rowNum));

        return landmark;
    }
}
