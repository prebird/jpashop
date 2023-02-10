package jpabook.jpashop.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long id;
    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
