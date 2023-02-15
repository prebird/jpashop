package jpabook.jpashop.api.dto.order;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.Orders;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class OrderDtoV2 {
    private Long id;
    private String memberName;
    private Address address;
    private List<OrderItemsDto> orderItems = new ArrayList<>(); //
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderDtoV2(Orders orders) {
        this.id = orders.getId();
        this.memberName = orders.getMember().getName();     // lazy loading 쿼리 전송
        this.address = orders.getDelivery().getAddress();
        this.orderItems = orders.getOrderItems().stream()
                .map(x -> new OrderItemsDto(x))
                .collect(Collectors.toList());
        this.orderDate = orders.getOrderDate();
        this.status = orders.getStatus();
    }
}
