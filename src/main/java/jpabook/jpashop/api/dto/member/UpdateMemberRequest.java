package jpabook.jpashop.api.dto.member;

import lombok.Data;

@Data
public class UpdateMemberRequest {
    private Long id;
    private String name;
}
