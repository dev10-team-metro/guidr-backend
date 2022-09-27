package learn.guidr.data;

import learn.guidr.data.mappers.LandmarkMapper;
import learn.guidr.models.Landmark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class LandmarkJdbcTemplateRepository implements LandmarkRepository{
    private final JdbcTemplate jdbcTemplate;

    public LandmarkJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Landmark> findAll() throws DataAccessException {

        final String sql = "select landmark_id, `name`, price, address_id, collection_id " +
                "from Landmarks;";

        return jdbcTemplate.query(sql, new LandmarkMapper());
    }

    @Override
    public Landmark findById(int id) throws DataAccessException {
        final String sql = "select landmark_id, `name`, price, address_id, collection_id " +
                "from Landmarks " +
                "where id = ?";

        return jdbcTemplate.query(sql, new LandmarkMapper(), id).stream().findFirst().orElse(null);
    }

    @Override
    public Landmark create(Landmark landmark) throws DataAccessException {

        final String sql = "insert into Landmarks (`name`, price, address_id, collection_id) " +
                "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, landmark.getName());
            statement.setBigDecimal(2, landmark.getPrice());
            statement.setInt(3, landmark.getAddress().getAddressId());
            statement.setInt(4, landmark.getCollection().getCollectionId());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        landmark.setLandmarkId(keyHolder.getKey().intValue());

        return landmark;
    }

    @Override
    public boolean update(Landmark landmark) throws DataAccessException {
        final String sql = "update Landmarks set " +
                "`name` = ?, " +
                "price = ?, " +
                "address_id = ?, " +
                "collection_id = ?, " +
                "where landmark_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                landmark.getLandmarkId(),
                landmark.getName(),
                landmark.getPrice(),
                landmark.getAddress().getAddressId(),
                landmark.getCollection().getCollectionId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Landmarks where landmark_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
