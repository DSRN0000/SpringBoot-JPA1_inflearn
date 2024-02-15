package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable //어딘가 내장될 수 있다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String street;
    private String zipcode;

//    protected Address() {  //@NoArgsConstructor(access = AccessLevel.PROTECTED) 애노테이션으로 대체
//    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
