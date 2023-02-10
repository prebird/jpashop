package jpabook.jpashop.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateMemberResponse {
    private Long id;
    private String name;
}
