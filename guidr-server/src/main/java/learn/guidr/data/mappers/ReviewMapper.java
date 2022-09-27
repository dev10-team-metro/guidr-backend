package learn.guidr.data.mappers;

import learn.guidr.models.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();

        review.setReviewId(rs.getInt("review_id"));
        review.setDescription(rs.getString("description"));
        review.setRating(rs.getBigDecimal("rating"));

        //collection mapper and appuser mapper

        review.setCollectionId(rs.getInt("collection_id"));

        review.setUserId(rs.getInt("user_id"));

        return review;
    }
}
