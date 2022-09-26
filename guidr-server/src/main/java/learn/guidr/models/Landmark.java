package learn.guidr.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Landmark {

    private int landmarkId;

    private String name;

    private BigDecimal price;

    private String address;

    private String city;

    private String state;

    private int zipCode;

    public Landmark(){

    }

    public Landmark(int landmarkId, String name, BigDecimal price, String address, String city, String state, int zipCode) {
        this.landmarkId = landmarkId;
        this.name = name;
        this.price = price;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Landmark landmark = (Landmark) o;
        return landmarkId == landmark.landmarkId && zipCode == landmark.zipCode && name.equals(landmark.name) && price.equals(landmark.price) && address.equals(landmark.address) && city.equals(landmark.city) && state.equals(landmark.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landmarkId, name, price, address, city, state, zipCode);
    }
}
