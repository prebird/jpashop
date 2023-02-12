package jpabook.jpashop.domain;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Orders order;
    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)    // ORDINAL 절대 쓰면 안되고 STRING으로 해야함
    private DeliveryStatus deliveryStatus;
}
