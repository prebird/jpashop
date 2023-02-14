package jpabook.jpashop.api.dto.order;

import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

@Getter
public class OrderItemsDto {

    private long id;
    private Orders order;
    private Item item;
    private int orderPrice;
    private int count;

    public OrderItemsDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.order = orderItem.getOrder();
        this.item = orderItem.getItem();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
