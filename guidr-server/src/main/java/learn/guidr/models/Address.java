package learn.guidr.models;

import java.util.Objects;

public class Address {

    private int addressId;

    private String address;

    private String city;

    private String state;

    private int zipCode;

    public Address(){

    }

    public Address(int address_id, String address, String city, String state, int zipCode) {
        this.addressId = address_id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return addressId == address1.addressId && zipCode == address1.zipCode && address.equals(address1.address) && city.equals(address1.city) && state.equals(address1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, city, state, zipCode);
    }
}
