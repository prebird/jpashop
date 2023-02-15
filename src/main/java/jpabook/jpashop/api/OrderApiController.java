package jpabook.jpashop.api;

import jpabook.jpashop.api.dto.order.OrderDto;
import jpabook.jpashop.api.dto.order.OrderDtoV2;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.repository.OrderRepository;
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
    public final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public Result ordersV1() {
        OrderSearch orderSearch = new OrderSearch();
        List<Orders> orders = orderService.findOrder(orderSearch);
        List<OrderDto> orderDtos = orders.stream()
                .map(x -> new OrderDto(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    // OrderDto로 감싸서 반환
    @GetMapping("/api/v2/orders")
    public Result ordersV2() {
        OrderSearch orderSearch = new OrderSearch();
        List<Orders> orders = orderRepository.findOrdersWithfetch();
        List<OrderDto> orderDtos = orders.stream()
                .map(x -> new OrderDto(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    //TODO: dto 직접 반환 Controller

    // OrderItems 포함(OrderItemsDto)
    @GetMapping("/api/v3/orders")
    public Result ordersV3() {
        OrderSearch orderSearch = new OrderSearch();
        List<Orders> orders = orderRepository.findAllByString(orderSearch);
        List<OrderDtoV2> orderDtos = orders.stream()
                .map(x -> new OrderDtoV2(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    @GetMapping("/api/v4/orders")
    public Result ordersV4() {
        OrderSearch orderSearch = new OrderSearch();
        List<Orders> orders = orderRepository.findAllByString(orderSearch);
        List<OrderDtoV2> orderDtos = orders.stream()
                .map(x -> new OrderDtoV2(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

}
