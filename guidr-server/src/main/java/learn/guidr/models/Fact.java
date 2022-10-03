package learn.guidr.models;

import java.util.Objects;

public class Fact {

    private int factId;

    private String description;

    private int landmarkId;

    public Fact(int factId, String description, int landmarkId) {
        this.factId = factId;
        this.description = description;
        this.landmarkId = landmarkId;
    }

    public Fact() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fact fact = (Fact) o;
        return factId == fact.factId && landmarkId == fact.landmarkId && description.equals(fact.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factId, description, landmarkId);
    }

    @Override
    public String toString() {
        return "Fact{" +
                "factId=" + factId +
                ", description='" + description + '\'' +
                ", landmarkId=" + landmarkId +
                '}';
    }
}
