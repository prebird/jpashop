package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); // 빈껍데기 라도 가져감
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setCity(memberForm.getCity());
        member.setStreet(memberForm.getStreet());
        member.setZipcode(memberForm.getZipcode());

        memberService.join(member);
        return "redirect:/";
    }
}
