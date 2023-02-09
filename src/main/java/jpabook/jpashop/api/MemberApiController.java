package jpabook.jpashop.api;

import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    public CreateMemberResponse saveMemberV1(@RequestMapping CreateMemberRequest req) {

    }

    public static class CreateMemberResponse {

    }

    public static class CreateMemberRequest {

    }


}
