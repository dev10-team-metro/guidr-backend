package learn.guidr.models;

import java.util.Objects;

public class Fact {

    private int factId;

    private String description;

    private String image;

    private int landmarkId;

    public Fact(int factId, String description, int landmarkId) {
        this.factId = factId;
        this.description = description;
        this.landmarkId = landmarkId;
    }

    public Fact() {

    }

    public Fact(int factId, String description, String image, int landmarkId) {
        this.factId = factId;
        this.description = description;
        this.image = image;
        this.landmarkId = landmarkId;
    }

    public int getFactId() {
        return factId;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fact fact = (Fact) o;
        return factId == fact.factId && landmarkId == fact.landmarkId && description.equals(fact.description) && Objects.equals(image, fact.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factId, description, image, landmarkId);
    }

    @Override
    public String toString() {
        return "Fact{" +
                "factId=" + factId +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", landmarkId=" + landmarkId +
                '}';
    }
}
