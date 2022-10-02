package learn.guidr.data;

import learn.guidr.data.mappers.FactMapper;
import learn.guidr.models.Fact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FactJdbcTemplateRepository implements FactRepository {

    private final JdbcTemplate jdbcTemplate;

    public FactJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Fact> findAll() throws DataAccessException {

        final String sql = "select facts_id, `description`, image, landmark_id " +
                "from Facts;";

        return jdbcTemplate.query(sql, new FactMapper());
    }

    @Override
    public List<Fact> findByLandmark(int landmarkId) throws DataAccessException {

        final String sql = "select f.facts_id, f.`description`, f.image, f.landmark_id " +
                "from Facts f " +
                "inner join Landmarks l on f.landmark_id = l.landmark_id " +
                "where l.landmark_id = ? ";

        return jdbcTemplate.query(sql, new FactMapper(), landmarkId);
    }

    @Override
    public Fact create(Fact fact) throws DataAccessException {

        final String sql = "insert into Facts (`description`, image, landmark_id) " +
                "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fact.getDescription());
            statement.setString(2, fact.getImage());
            statement.setInt(3, fact.getLandmarkId());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        fact.setFactId(keyHolder.getKey().intValue());

        return fact;
    }

    @Override
    public boolean update(Fact fact) throws DataAccessException {
        final String sql = "update Facts set " +
                "`description` = ?, " +
                "image = ? " +
                "landmark_id = ? " +
                "where facts_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                fact.getDescription(),
                fact.getImage(),
                fact.getLandmarkId(),
                fact.getFactId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Facts where facts_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

}
