package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.BaseEntity;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@DiscriminatorColumn(name = "DTYPE")    // 구분 컬럼의 컬럼명
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToMany(mappedBy = "items")
    private List<Category> category = new ArrayList<>();

    private String name;
    private int price;
    private int stockQuantity;

    // 생성자
    public Item(Long id, List<Category> category ,String name, int price, int stockQuantity) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    //==생성 메서드==//
    public static Item createItem(Long id, String name, int price, int stockQuantity) {
        Item item = new Item(id, null, name, price, stockQuantity);
        return item;
    }

    public static Item createItem(String name, int price, int stockQuantity) {
        Item item = new Item(name, price, stockQuantity);
        return item;
    }

    //==비즈니스로직==//
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    /**
     * 필드 수정을 위한 메서드
     */
    public void change(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
