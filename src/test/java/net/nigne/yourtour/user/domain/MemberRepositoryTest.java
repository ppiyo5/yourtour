package net.nigne.yourtour.user.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void Member_조회() {

        //given
        Member member = createMember();

        //when
        Optional<Member> result = memberRepository.findById("test");

        //then
        assertThat(result.get().getId()).isEqualTo(member.getId());
    }

    public Member createMember() {
        return memberRepository.save(Member.createBuilder()
                .id("test")
                .name("테스트")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .email("test@test.com")
                .address("서울")
                .build()
        );
    }

}