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

<<<<<<< HEAD
        //Taddress mapper and sitecollection mapper
=======
        //address mapper and sitecollection mapper
>>>>>>> origin

        AddressMapper addressMapper = new AddressMapper();
        landmark.setAddress(addressMapper.mapRow(rs, rowNum));

<<<<<<< HEAD
        CollectionMapper collectionMapper = new CollectionMapper();
        landmark.setCollection(collectionMapper.mapRow(rs, rowNum));
=======
        landmark.setCollectionId(rs.getInt("collection_id"));
>>>>>>> origin

        return landmark;
    }
}
