package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")    // 구분 컬럼의 값
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book extends Item {
    private String author;
    private String isbn;
}
