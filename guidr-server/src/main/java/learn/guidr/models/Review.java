package learn.guidr.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Review {

    private int reviewId;

    private String description;

    private BigDecimal rating;

    private int userId;

<<<<<<< HEAD
    private SiteCollection collection;
=======
    private int collectionId;
>>>>>>> origin

    public Review(){

    }

<<<<<<< HEAD
    public Review(int reviewId, String description, BigDecimal rating, int userId, SiteCollection collection) {
=======
    public Review(int reviewId, String description, BigDecimal rating, int userId, int collectionId) {
>>>>>>> origin
        this.reviewId = reviewId;
        this.description = description;
        this.rating = rating;
        this.userId = userId;
<<<<<<< HEAD
        this.collection = collection;
=======
        this.collectionId = collectionId;
>>>>>>> origin
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

<<<<<<< HEAD
    public SiteCollection getCollection() {
        return collection;
    }

    public void setCollection(SiteCollection collection) {
        this.collection = collection;
=======
    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
>>>>>>> origin
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
<<<<<<< HEAD
        return reviewId == review.reviewId && userId == review.userId && Objects.equals(description, review.description) && rating.equals(review.rating) && collection.equals(review.collection);
=======
        return reviewId == review.reviewId && userId == review.userId && collectionId == review.collectionId && Objects.equals(description, review.description) && rating.equals(review.rating);
>>>>>>> origin
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(reviewId, description, rating, userId, collection);
=======
        return Objects.hash(reviewId, description, rating, userId, collectionId);
>>>>>>> origin
    }
}
