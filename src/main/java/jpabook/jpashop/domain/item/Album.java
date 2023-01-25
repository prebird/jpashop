package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("A")    // 구분 컬럼의 값
@PrimaryKeyJoinColumn(name = "BOOK_ID") // ID 재정의
public class Album extends Item {
    private String artist;
}
