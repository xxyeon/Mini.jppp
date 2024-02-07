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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //스프링 시큐리티가 낚아챈다 -> SecurityConfig 파일 생성 후 낚아채지 않음
    @GetMapping("/main-page")
    public String login() {
        return "main/index";
    }

}
