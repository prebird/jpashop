package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    private int orderPrice;
    private int count;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비스니스 로직==//
    public void cancel() {
        this.getItem().addStock(count);
    }

    //==조회 로직==//
    public int getTotalPrice() {
        return orderPrice * count;
    }


}
