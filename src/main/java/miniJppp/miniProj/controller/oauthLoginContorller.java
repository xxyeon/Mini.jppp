package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.DTO.MemberDto;
import miniJppp.miniProj.config.auth.PrincipalDetails;
import miniJppp.miniProj.entity.Chapter;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.ChapterRespository;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import miniJppp.miniProj.repository.WordRepository;
import miniJppp.miniProj.service.InventoryService;
import miniJppp.miniProj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class oauthLoginContorller {

    private final MemberService memberService;
    private final ChapterRespository chapterRespository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    //스프링 시큐리티가 낚아챈다 -> SecurityConfig 파일 생성 후 낚아채지 않음
    @GetMapping("/main-page")
    public String login() {
        return "/main/index";
    }


    @PostMapping("/join")
    public String join(Member member) {
        System.out.println("Member: " + member.getEmail());
        String encPassword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encPassword);
        memberRepository.save(member);
//        memberService.saveMember(member);
        return "redirect:/main-page";
    }

    /* 로그인 오류시 호출*/
    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/main/user-login";
    }

    @GetMapping("/")
    public String memberMainPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        System.out.println("user객체(PrincipalDetails.getUser: " + principalDetails.getUser());


        List<Chapter> chapters = chapterRespository.findAll();
        System.out.println("chapters: " + chapters.get(0).getNumber());
        model.addAttribute("chapters", chapters);
        return "/main/ww_main";
    }

}
