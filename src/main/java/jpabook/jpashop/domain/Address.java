package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙 상 기본생성자가 꼭 있어야한다.(Protected로 new 못하게)
    // 리플렉션, 프록시 등 기술을 JPA가 사용할 수 있도록 하기 위함
    protected Address() {}      

    public Address(String city, String street, String zipcode) {

    }
}
