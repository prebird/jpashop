package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")    // 구분 컬럼의 값
public class Movie extends Item {
    private String director;
    private String actor;
}
