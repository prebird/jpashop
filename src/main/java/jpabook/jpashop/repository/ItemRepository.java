package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null) {
            em.persist(item);
        }
        //em.merge(item); // update 비슷
        // merge 보다 변경감지 사용하기
        Item findItem = em.find(Item.class, item.getId());  // 조회 후 영속성 상태
        findItem.change(item.getName(), item.getPrice(), item.getStockQuantity()); // 값 수정
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public Book findOneBook(Long id) {
        return em.find(Book.class, id);
    }

    public List<Item> findAll() { // JPQL : 객체를 대상으로 쿼리
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
