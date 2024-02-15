package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) //junit 실행할 때, 스프링이랑 같이 엮어서 실행한다는 뜻
@SpringBootTest //스프링부트를 띄운 상태로 테스트 할 때 필요. 없으면 @Autowired 다 실패한다.
@Transactional //테스트가 끝나면 롤백을 해준다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Rollback(false) // 실제 db에 값 들어있는거 보고 싶으면 롤백 false 해보기
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class) //예외사항 미리 적어준다.
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다!!!

        // @Test(expected = IllegalStateException.class) 넣어줬기 때문에 위와같이 간단하게 작성 가능하다.
//        try {
//            memberService.join(member2); //예외가 발생해야 한다!!
//        } catch (IllegalStateException e){
//            return;
//        }

        //then
        fail("예외가 발생해야 한다."); //여기까지 내려오면 안된다는 뜻. fail 예외 발생된다.
    }
}