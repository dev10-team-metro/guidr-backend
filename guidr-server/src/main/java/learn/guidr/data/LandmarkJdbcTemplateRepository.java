package learn.guidr.data;

import learn.guidr.data.mappers.FactMapper;
import learn.guidr.data.mappers.LandmarkMapper;
import learn.guidr.models.Landmark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LandmarkJdbcTemplateRepository implements LandmarkRepository{
    private final JdbcTemplate jdbcTemplate;

    public LandmarkJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Landmark> findAll() throws DataAccessException {

        final String sql = "select l.landmark_id, l.`name`, l.price, l.address_id, l.collection_id, a.address, a.zip_code, a.city, a.state " +
                "from Landmarks l " +
                "inner join Address a on l.address_id = a.address_id ";

        List<Landmark> landmarks = jdbcTemplate.query(sql, new LandmarkMapper());
        if (landmarks != null){
            for(int i = 0; i < landmarks.size(); i++){
                addFacts(landmarks.get(i));
            }
        }
        return landmarks;
    }

    @Override
    public Landmark findById(int id) throws DataAccessException {
        final String sql = "select l.landmark_id, l.`name`, l.price, l.address_id, l.collection_id, a.address, a.zip_code, a.city, a.state " +
                "from Landmarks l " +
                "inner join Address a on l.address_id = a.address_id " +
                "where l.landmark_id = ?";

       Landmark result = jdbcTemplate.query(sql, new LandmarkMapper(), id).stream().findFirst().orElse(null);

        if(result != null){
            addFacts(result);
        }
        return result;
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
            statement.setInt(4, landmark.getCollectionId());
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
                "collection_id = ? " +
                "where landmark_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                landmark.getName(),
                landmark.getPrice(),
                landmark.getAddress().getAddressId(),
                landmark.getCollectionId(),
                landmark.getLandmarkId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Landmarks where landmark_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private void addFacts(Landmark landmark) {

        final String sql = "select l.landmark_id, l.`name`, l.price, l.address_id, l.collection_id, f.facts_id, f.`description` "
                + "from Landmarks l "
                + "inner join Facts f on l.landmark_id = f.landmark_id "
                + "where l.landmark_id = ?";

        var facts = jdbcTemplate.query(sql, new FactMapper(), landmark.getLandmarkId());
        landmark.setFacts(facts);
    }
}
