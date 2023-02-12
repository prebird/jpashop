package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit() {
            Member member = Member.builder()
                    .name("userA")
                    .address(new Address("서울", "11", "111"))
                    .build();
            em.persist(member);

            Book book1 = (Book)Book.createItem("JPA BOOK", 10000, 10);
            Book book2 = (Book)Book.createItem("JPA BOOK2", 20000, 10);
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = Delivery.builder()
                    .address(member.getAddress())
                    .build();
            Orders order = Orders.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
