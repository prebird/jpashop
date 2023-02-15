package jpabook.jpashop.api.dto.order;

import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemsDto {

    private long id;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemsDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
