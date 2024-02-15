package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 등록
     */
    @GetMapping("/members/new") //이 사이트로 들어오면 return문의 html이 열린다.
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm()); //컨트롤러에서 view로 넘어갈 때 이 데이터를 실어서 넘긴다.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        if (result.hasErrors()){  //에러가 있으면 아래 페이지로 보낸다.
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; // 재로딩 되거나 하면 안좋기 때문에 첫번째 페이지로 보낸다.
    }

    /**
     * 회원 목록 조회
     */

    @GetMapping("/members")
    public String list(Model model){

        model.addAttribute("members", memberService.findMembers()); //아래 두줄과 같은 문법

//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
        return "members/memberList";
    }
}
