package jpabook.jpashop.domain;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Orders order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    // ORDINAL 절대 쓰면 안되고 STRING으로 해야함
    private DeliveryStatus deliveryStatus;

    protected Delivery() {}
}
