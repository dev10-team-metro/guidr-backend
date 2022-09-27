package learn.guidr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SiteCollection {

    private int collectionId;

    private String name;

    private String description;

    private List<Landmark> landmarks = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    public SiteCollection(){

    }

    public SiteCollection(int collectionId, String name, String description) {
        this.collectionId = collectionId;
        this.name = name;
        this.description = description;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteCollection that = (SiteCollection) o;
        return collectionId == that.collectionId && name.equals(that.name) && description.equals(that.description) && landmarks.equals(that.landmarks) && Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, name, description, landmarks, reviews);
    }
}
