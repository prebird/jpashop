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
    private String memberName;  // 양방향 연관관계 -> 무한루프 -> 이름만 출력
    private Address address;  // 양방향 연관관계 -> 무한루프 -> 주소만 출력
    //private List<OrderItemsDto> orderItems = new ArrayList<>(); //
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderDto(Orders orders) {
        this.id = orders.getId();
        this.memberName = orders.getMember().getName();     // lazy loading 쿼리 전송
        this.address = orders.getDelivery().getAddress();   // lazy loading 쿼리 전송
        //this.orderItems = orders.getOrderItems().stream()
        //        .map(x -> new OrderItemsDto(x))
        //        .collect(Collectors.toList());
        this.orderDate = orders.getOrderDate();
        this.status = orders.getStatus();
    }

}
