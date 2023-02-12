package jpabook.jpashop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    // 컬렉션은 필드에서 초기화 하자 주의사항**
    // null exception 방지
    // 하이버네이트 컬렉션으로 변경되기 때문에 조회 후 변경하면 안됨
    @OneToMany
    private List<Orders> orders = new ArrayList<>();

    public Member() {}
    public Member(String name) {
        this.name = name;
    }

    public Member(Long id, String name) {
        this.name = name;
    }
}
