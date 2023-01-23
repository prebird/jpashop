package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // 주문
    public void order(Member member, OrderItem... orderItems) {
        //orderItem? - 재고 감소

        Order order = Order.createOrder(member, orderItems);
        orderRepository.save(order);
    }

    // 주문취소
    public void cancel(Long orderId) {
        Order findOrder = orderRepository.findOne(orderId);
        findOrder.cancel();
    }

    // 조회
    public void getOrder() {
        //orderRepository
    }

}
