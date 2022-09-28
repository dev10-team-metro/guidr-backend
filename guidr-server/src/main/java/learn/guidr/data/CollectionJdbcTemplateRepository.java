package learn.guidr.data;

import learn.guidr.data.mappers.CollectionMapper;
import learn.guidr.data.mappers.LandmarkMapper;
import learn.guidr.data.mappers.ReviewMapper;
import learn.guidr.models.SiteCollection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CollectionJdbcTemplateRepository implements CollectionRepository{

    public final JdbcTemplate jdbcTemplate;

    public CollectionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SiteCollection> findAll() throws DataAccessException {

        final String sql = "select collection_id, `name`, `description` " +
                "from Collection;";

        return jdbcTemplate.query(sql, new CollectionMapper());
    }

    @Override
    public SiteCollection findById(int id) throws DataAccessException {
        final String sql = "select collection_id, `name`, `description` " +
                "from Collection " +
                "where collection_id = ?;";


        SiteCollection result = jdbcTemplate.query(sql, new CollectionMapper(), id).stream().findFirst().orElse(null);

        if(result != null){
            addLandmarks(result);
            addReviews(result);
        }
        return result;
    }

    @Override
    public List<SiteCollection> findByCity(String city, String state) throws DataAccessException {

        final String sql = "select c.collection_id, c.`name`, c.`description` " +
                "from Collection c " +
                "inner join Landmarks l on c.collection_id = l.collection_id " +
                "inner join Address a on l.address_id = a.address_id " +
                "where a.city = ? and a.state = ? " +
                "group by c.collection_id, c.`name`, c.`description` ";


        List<SiteCollection> result = jdbcTemplate.query(sql, new CollectionMapper(), city, state);

        for (int i = 0; i < result.size(); i++){
            addLandmarks(result.get(i));
            addReviews(result.get(i));
        }

        return result;

    }

    @Override
    public SiteCollection create(SiteCollection collection) throws DataAccessException {

        final String sql = "insert into Collection (`name`, `description`) " +
                "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, collection.getName());
            statement.setString(2, collection.getDescription());
            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        collection.setCollectionId(keyHolder.getKey().intValue());

        return collection;
    }

    @Override
    public boolean update(SiteCollection collection) throws DataAccessException {
        final String sql = "update Collection set " +
                "`name` = ?, " +
                "`description` = ? " +
                "where collection_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                collection.getName(),
                collection.getDescription(),
                collection.getCollectionId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Collection where collection_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }



    private void addLandmarks(SiteCollection collection) {

        final String sql = "select l.landmark_id, l.`name`, l.price, l.address_id, l.collection_id, a.address, a.zip_code, a.city, a.state "
                + "from Landmarks l "
                + "inner join Address a on l.address_id = a.address_id "
                + "where collection_id = ?";

        var landmarks = jdbcTemplate.query(sql, new LandmarkMapper(), collection.getCollectionId());
        collection.setLandmarks(landmarks);
    }

    private void addReviews(SiteCollection collection) {

        final String sql = "select review_id, `description`, rating, collection_id, user_id "
                + "from Reviews "
                + "where collection_id = ?";

        var reviews = jdbcTemplate.query(sql, new ReviewMapper(), collection.getCollectionId());
        collection.setReviews(reviews);
    }
}
