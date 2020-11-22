package iot.server.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String code;
    private String position;

    protected Address(){

    }

    public Address(String city, String street, String code, String position) {
        this.city = city;
        this.street = street;
        this.code = code;
        this.position = position;
    }
}
