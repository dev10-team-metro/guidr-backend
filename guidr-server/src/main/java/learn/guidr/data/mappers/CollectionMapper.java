package learn.guidr.data.mappers;

import learn.guidr.models.SiteCollection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionMapper implements RowMapper<SiteCollection> {

    @Override
    public SiteCollection mapRow(ResultSet rs, int rowNum) throws SQLException {
        SiteCollection collection = new SiteCollection();

        collection.setCollectionId(rs.getInt("collectionId"));
        collection.setName(rs.getString("name"));
        collection.setDescription(rs.getString("description"));

        return collection;
    }

}
