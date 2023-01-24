package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    // 이거 때문에 OrderItem에도 연관관계 메서드를 추가해야되지 않나?
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]


    //==연관관계 메서드==/
    // 양방향 관계일 때, 연관관계 메서드 - 관계 주인에 만듬
    public void setMember(Member member) {
        // 기존 연관관계 제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //==생성 메서드==//
    public static Orders createOrder(Member member, OrderItem... orderItems) {
        Orders order = new Orders();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //== 비즈니스 로직==//
    public void cancel() {
        // TODO: 이미 배송된 것은 취소 불가
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //== 조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

}
