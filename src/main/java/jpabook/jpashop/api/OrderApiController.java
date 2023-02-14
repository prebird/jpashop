package jpabook.jpashop.api;

import jpabook.jpashop.api.dto.member.MemberDto;
import jpabook.jpashop.api.dto.order.OrderDto;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    public final OrderService orderService;

    @GetMapping("/api/v1/orders")
    public Result ordersV1() {
        OrderSearch orderSearch = new OrderSearch();
        List<Orders> orders = orderService.findOrder(orderSearch);
        List<OrderDto> orderDtos = orders.stream()
                .map(x -> new OrderDto(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

}
