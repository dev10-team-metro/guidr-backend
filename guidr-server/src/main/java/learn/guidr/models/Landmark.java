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

    private SiteCollection collection;


    public Landmark(){

    }

    public Landmark(int landmarkId, String name, BigDecimal price, Address address, SiteCollection collection) {
        this.landmarkId = landmarkId;
        this.name = name;
        this.price = price;
        this.address = address;
        this.collection = collection;
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

    public SiteCollection getCollection() {
        return collection;
    }

    public void setCollection(SiteCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Landmark landmark = (Landmark) o;
        return landmarkId == landmark.landmarkId && name.equals(landmark.name) && price.equals(landmark.price) && address.equals(landmark.address) && collection.equals(landmark.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landmarkId, name, price, address, collection);
    }
}
