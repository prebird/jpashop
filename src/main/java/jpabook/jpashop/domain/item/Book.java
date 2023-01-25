package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")    // 구분 컬럼의 값
@NoArgsConstructor
@Getter
public class Book extends Item {
    public Book (Long id, List<Category> category , String name, int price, int stockQuantity,
                 String author, String isbn) {
        super(id, category, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
    private String author;
    private String isbn;
}
