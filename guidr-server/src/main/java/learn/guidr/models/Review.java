package learn.guidr.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Review {

    private int reviewId;

    private String description;

    private BigDecimal rating;

    private int userId;

    public Review(){

    }

    public Review(int reviewId, String description, BigDecimal rating, int userId) {
        this.reviewId = reviewId;
        this.description = description;
        this.rating = rating;
        this.userId = userId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId && userId == review.userId && Objects.equals(description, review.description) && rating.equals(review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, description, rating, userId);
    }
}
