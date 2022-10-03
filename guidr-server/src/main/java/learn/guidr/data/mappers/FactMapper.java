package learn.guidr.data.mappers;

import learn.guidr.models.Address;
import learn.guidr.models.Fact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactMapper implements RowMapper<Fact> {
    @Override
    public Fact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fact fact = new Fact();

        fact.setFactId(rs.getInt("facts_id"));
        fact.setDescription(rs.getString("description"));
        fact.setImage(rs.getString("image"));
        fact.setLandmarkId(rs.getInt("landmark_id"));

        return fact;
    }
}
