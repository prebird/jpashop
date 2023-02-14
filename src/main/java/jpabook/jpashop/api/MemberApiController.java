package jpabook.jpashop.api;

import jpabook.jpashop.api.dto.member.*;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> members = memberService.findMembers();
        List<MemberDto> memberDtos = members.stream()
                .map(x -> new MemberDto(x))
                .collect(Collectors.toList());

        return new Result(memberDtos);
    }


}
