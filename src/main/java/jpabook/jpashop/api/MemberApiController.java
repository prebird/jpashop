package jpabook.jpashop.api;

import jpabook.jpashop.api.dto.member.CreateMemberRequest;
import jpabook.jpashop.api.dto.member.CreateMemberResponse;
import jpabook.jpashop.api.dto.member.UpdateMemberRequest;
import jpabook.jpashop.api.dto.member.UpdateMemberResponse;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody CreateMemberRequest req) {
        Member member = new Member(req.getName());

        Long joinedId = memberService.join(member);

        return new CreateMemberResponse(joinedId);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable Long id,
                                               @RequestBody UpdateMemberRequest req) {

        Long updatedId = memberService.update(id, req.getName());
        Member member = memberService.findOne(updatedId);

        return UpdateMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }


}
