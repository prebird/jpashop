package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")    // 구분 컬럼의 값
@Getter
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {}
    public Book (Long id, List<Category> category , String name, int price, int stockQuantity,
                 String author, String isbn) {
        super(id, category, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public Book (String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }

    public static Book createItem(String name, int price, int stockQuantity) {
        return new Book(name, price, stockQuantity);
    }
}
