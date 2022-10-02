package learn.guidr.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Address {

    private int addressId;

    private String address;

    private String city;

    private String state;

    private int zipCode;

    private BigDecimal latitude;

    private BigDecimal longitude;

    public Address(){

    }

    public Address(int address_id, String address, String city, String state, int zipCode) {
        this.addressId = address_id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(int addressId, String address, String city, String state, int zipCode, BigDecimal latitude, BigDecimal longitude) {
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return addressId == address1.addressId && zipCode == address1.zipCode && address.equals(address1.address) && city.equals(address1.city) && state.equals(address1.state) && Objects.equals(latitude, address1.latitude) && Objects.equals(longitude, address1.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, city, state, zipCode, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
