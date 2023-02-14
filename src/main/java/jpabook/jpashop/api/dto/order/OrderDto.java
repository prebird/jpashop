package jpabook.jpashop.api.dto.order;

import jpabook.jpashop.domain.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDto {
    private Long id;
    private Member member;
    private Delivery delivery;
    private List<OrderItemsDto> orderItems = new ArrayList<>(); // OrderItemsDto로 바꾼다.
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderDto(Orders orders) {
        this.id = orders.getId();
        this.member = orders.getMember();
        this.delivery = orders.getDelivery();
        this.orderItems = orders.getOrderItems().stream()
                .map(x -> new OrderItemsDto(x))
                .collect(Collectors.toList());
        this.orderDate = orders.getOrderDate();
        this.status = orders.getStatus();
    }

}
