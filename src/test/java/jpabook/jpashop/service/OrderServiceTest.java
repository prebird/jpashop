package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    void 상품_주문_테스트() {
        Member member = createMember("member1");
        Item item = createItem("ORM을 다루는 가술 JPA", 10000, 10);

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), 2);

        // then
        Order findOrder = orderRepository.findOne(orderId);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER)
                .withFailMessage("상품 주문시 상태는 STATUS");
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1)
                .withFailMessage("주문한 상품 종류가 정확해야한다.");
        assertThat(findOrder.getTotalPrice()).isEqualTo(10000 * 2)
                .withFailMessage("주문 가격은 가격 * 수량이다.");
        assertThat(item.getStockQuantity()).isEqualTo(8)
                .withFailMessage("주문 수량 만큼 재고가 빠져야한다.");
    }

    private Item createItem(String itemName, int price, int stockQuantity) {
        Item item = Item.createItem(null, itemName, price, stockQuantity);
        em.persist(item);
        return item;
    }

    private Member createMember(String member1) {
        Member member = new Member();
        member.setName(member1);
        em.persist(member);
        return member;
    }

    @Test
    void 재고수량_초과_테스트() {
        Member member = createMember("member1");
        Item item = createItem("ORM을 다루는 가술 JPA", 10000, 10);
        int orderCount = 11;

        assertThatThrownBy(() -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        }).isInstanceOf(NotEnoughStockException.class)
                .hasMessage("need more stock");
    }

    @Test
    void 주문_취소_테스트() {
        Member member = createMember("member1");
        Item item = createItem("ORM을 다루는 가술 JPA", 10000, 10);
        Long orderId = orderService.order(member.getId(), item.getId(), 2);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order order = orderRepository.findOne(orderId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL)
                .withFailMessage("주문 상태는 cancel 여야한다.");
        assertThat(item.getStockQuantity()).isEqualTo(10)
                .withFailMessage("재고량이 다시 증가해야한다.");
    }
}
