package miniJppp.miniProj.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import miniJppp.miniProj.DTO.MemberDto;
import miniJppp.miniProj.config.auth.PrincipalDetails;
import miniJppp.miniProj.entity.*;
import miniJppp.miniProj.repository.*;
import miniJppp.miniProj.service.InventoryService;
import miniJppp.miniProj.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/main")
public class WordController {

    private final WordRepository wordRepository;
    private final InventoryRepository inventoryRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ChapterRespository chapterRespository;
    private final InventoryService inventoryService;
    private final LearnRepository learnRepository;

    @GetMapping("/learn/{chapterId}")
    public String learn(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("chapterId") Long chapter_id, Model model) throws SQLException {
        System.out.println(principalDetails.getUser().getEmail());
        List<Word> words = wordRepository.findAllByChapter_Id(chapter_id);
        model.addAttribute("words", words);
        int wordCount = words.size();
        model.addAttribute("wordTotal", wordCount);
        return"/main/ww_learn";
    }

    //인벤토리 api

    @GetMapping("/inventory") //인벤토리 조회
    public ModelAndView inventory(@AuthenticationPrincipal PrincipalDetails principalDetails, ModelAndView modelAndView) {
        Member member = principalDetails.getUser();
        MemberDto memberDto = MemberDto.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .profileImgUrl(member.getProfileImgUrl()).build();
        System.out.println(member);
        Inventory memberInventory= inventoryService.getInventoryByName(memberDto.getNickname());
        List<Word> words = inventoryService.getInventoryWordList(memberInventory);
        List<Learn> learns = learnRepository.findAllByInventory(memberInventory);

        List<Chapter> chapters = chapterRespository.findAll();

        modelAndView.addObject("chapters", chapters);
        modelAndView.addObject("words", words);
        modelAndView.addObject("learns", learns);
        modelAndView.addObject("member",memberDto);

        modelAndView.setViewName("/main/inventory");

        return modelAndView;
    }

    @GetMapping("/miniGame")
    public String miniGame(Model model) {
        List<Chapter> chapters = chapterRespository.findAll();
        model.addAttribute("chapters", chapters);
        return"/main/miniGame";
    }

    @GetMapping("/tictactoe")
    public String tictactoe() {
        return"/main/tictactoe";
    }

    @GetMapping("/hangman")
    public String hangman() {
        return"/main/hangman";
    }

    @GetMapping("/baseball")
    public String baseball() {
        return"/main/baseball";
    }

    @GetMapping("/levelUp")
    public String levelUp() {
        return"/main/levelUp";
    }

    @GetMapping("/snake")
    public String snake() {
        return"/main/snake";
    }

    @GetMapping("/profile")
    public String profile() {
        return"/main/profile";
    }
}
