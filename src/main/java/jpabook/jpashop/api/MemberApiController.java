package jpabook.jpashop.api;

import jpabook.jpashop.api.dto.member.CreateMemberRequest;
import jpabook.jpashop.api.dto.member.CreateMemberResponse;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
