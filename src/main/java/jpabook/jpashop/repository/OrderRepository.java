package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Orders order) {
        em.persist(order);
    }

    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }
   
    // 조건에 따른 조회기능
    public List<Orders> findAllByString(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> o = cq.from(Orders.class);
        Join<Orders, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Orders> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }

    public List<Orders> findOrdersWithfetch() {
        return em.createQuery(
                "select o from Orders o " +
                        " join fetch o.member m" +
                        " join fetch o.delivery d")
                .getResultList();
    }


    public List<Orders> findOrdersWithFetchItem() {
        return em.createQuery(
                        "select distinct o from Orders o " +
                                " join fetch o.member m" +
                                " join fetch o.delivery d" +
                                " join fetch o.orderItems oi" +
                                " join fetch oi.item i")
                .getResultList();
    }
}
