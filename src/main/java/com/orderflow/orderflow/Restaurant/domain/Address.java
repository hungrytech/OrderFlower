package com.orderflow.orderflow.Restaurant.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipCode;

    public boolean checkInfo() {
        return city != null && street != null && zipCode != null;
    }

    public boolean isSame(Address address) {
        return (city.equals(address.getCity())) &&
                (street.equals(address.getStreet())) &&
                (zipCode.equals(address.getZipCode()));
    }
}
