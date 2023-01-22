package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null) {
            em.persist(item);
        }
        em.merge(item); // update 비슷
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { // JPQL : 객체를 대상으로 쿼리
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
