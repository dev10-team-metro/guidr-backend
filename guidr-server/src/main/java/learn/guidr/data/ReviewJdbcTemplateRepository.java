package learn.guidr.data;

import learn.guidr.data.mappers.ReviewMapper;
import learn.guidr.models.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ReviewJdbcTemplateRepository implements ReviewRepository{
    private final JdbcTemplate jdbcTemplate;

    public ReviewJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> findAll() throws DataAccessException {

        final String sql = "select review_id, `description`, rating, collection_id, user_id " +
                "from Reviews;";

        return jdbcTemplate.query(sql, new ReviewMapper());
    }

    @Override
    public Review create(Review review) throws DataAccessException {

        final String sql = "insert into Reviews (`description`, rating, collection_id, user_id) " +
                "values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, review.getDescription());
            statement.setBigDecimal(2, review.getRating());
            statement.setInt(3, review.getCollection().getCollectionId());
            statement.setInt(4, review.getUserId());

            return statement;
        }, keyHolder);

        if (rowsAffected == 0) {
            return null;
        }

        review.setReviewId(keyHolder.getKey().intValue());

        return review;
    }

    @Override
    public boolean update(Review review) throws DataAccessException {
        final String sql = "update Reviews set " +
                "`description` = ?, " +
                "rating = ?, " +
                "user_id = ?, " +
                "collection_id = ?, " +
                "where review_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                review.getReviewId(),
                review.getDescription(),
                review.getRating(),
                review.getUserId(),
                review.getCollection().getCollectionId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        final String sql = "delete from Reviews where review_id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

}
