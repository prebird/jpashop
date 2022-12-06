package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;



    @Test
    @Transactional
    @Rollback(value = false)
    // from Spring framework, EntityManager 사용시 필요
    // @Transactional이 Test에 있으면 바로 롤백시킴
    void testMember() throws Exception{
        Member member = new Member();
        member.setUsername("memberA");

        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // findMember == member 인 이유
        Assertions.assertThat(findMember).isEqualTo(member);
        // 같은 것을 저장하고 조회를 하기 때문에,
        // 하나의 영속성 컨텍스트 안에서는 id값이 같으면 같은 엔티티로 식별된다.
        // 1차 캐시에서 영속성 컨텍스트에 똑같은게 있기 때문에 그것을 반환한 것이다.
    }

    @Test
    void find() {
    }
}