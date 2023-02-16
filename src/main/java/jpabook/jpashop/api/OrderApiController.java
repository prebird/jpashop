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
        List<Orders> orders = orderRepository.findOrdersWithFetch();
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

    // fetch join + distinct
    @GetMapping("/api/v4/orders")
    public Result ordersV4() {
        List<Orders> orders = orderRepository.findOrdersWithFetchItem();
        List<OrderDtoV2> orderDtos = orders.stream()
                .map(x -> new OrderDtoV2(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    @GetMapping("/api/v5/orders")
    public Result ordersV5() {
        List<Orders> orders = orderRepository.findOrdersWithFetch();    // XtoOne 관계 데이터 fetch
        List<OrderDtoV2> orderDtos = orders.stream()    // XtoMany 데이터 배치설정 주고 한번에
                .map(x -> new OrderDtoV2(x))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

}
