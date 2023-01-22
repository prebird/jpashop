package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional //롤백
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kangHaeLin");

        // when
        Long savedId = memberService.join(member);

        // then
        assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member = new Member();
        member.setName("kangHaeLin");

        Member member2 = new Member();
        member2.setName("kangHaeLin");

        // when
        Long savedId = memberService.join(member);
        assertThatThrownBy(() -> {
            Long savedId2 = memberService.join(member2);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");
    }

}
