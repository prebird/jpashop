package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    //@Rollback(value = false)
    // from Spring framework, EntityManager 사용시 필요
    // @Transactional이 Test에 있으면 바로 롤백시킴
    void Member_save_findOne() throws Exception {
        Member member = new Member();
        member.setName("memberA");

        Long saveId = memberRepository.save(member).getId();
        Member findMember = memberRepository.findById(saveId).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());

        // findMember == member 인 이유
        assertThat(findMember == member);
        // 같은 것을 저장하고 조회를 하기 때문에,
        // 하나의 영속성 컨텍스트 안에서는 id값이 같으면 같은 엔티티로 식별된다.
        // 1차 캐시에서 영속성 컨텍스트에 똑같은게 있기 때문에 그것을 반환한 것이다.
    }

    @Test
    @Transactional
    void Member_save_findAll() {
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");
        // save
        memberRepository.save(member1);
        memberRepository.save(member2);
        // findAll
        List<Member> allMember = memberRepository.findAll();
        // answer
        List<Member> answer = new ArrayList<>();
        answer.add(member1);
        answer.add(member2);

        assertThat(allMember).isEqualTo(answer);
    }

    @Test
    @Transactional
    void Member_save_findByName() {
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member2.setName("member2");
        // save
        memberRepository.save(member1);
        memberRepository.save(member2);
        // findByName
        List<Member> findMembers = memberRepository.findByName("member1");

        assertThat(findMembers.get(0)).isEqualTo(member1);
    }
}
