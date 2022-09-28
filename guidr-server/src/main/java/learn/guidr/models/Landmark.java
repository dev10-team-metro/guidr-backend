package learn.guidr.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Landmark {

    private int landmarkId;

    private String name;

    private BigDecimal price;

    private Address address;

    private int collectionId;


    public Landmark(){

    }

    public Landmark(int landmarkId, String name, BigDecimal price, Address address, int collectionId) {
        this.landmarkId = landmarkId;
        this.name = name;
        this.price = price;
        this.address = address;
        this.collectionId = collectionId;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Landmark landmark = (Landmark) o;
        return landmarkId == landmark.landmarkId && collectionId == landmark.collectionId && name.equals(landmark.name) && Objects.equals(price, landmark.price) && address.equals(landmark.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landmarkId, name, price, address, collectionId);
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "landmarkId=" + landmarkId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", address=" + address +
                ", collectionId=" + collectionId +
                '}';
    }
}
