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
