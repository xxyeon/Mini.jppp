package miniJppp.miniProj.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import miniJppp.miniProj.DTO.MemberDto;
import miniJppp.miniProj.entity.Chapter;
import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.entity.Word;
import miniJppp.miniProj.repository.ChapterRespository;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import miniJppp.miniProj.repository.WordRepository;
import miniJppp.miniProj.service.InventoryService;
import miniJppp.miniProj.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @GetMapping("/{name}")
    public String memberMainPage(@PathVariable String name, Model model) {

        MemberDto memberDto = memberService.saveMember(new Member(name, "", LocalDateTime.now()));

        List<Chapter> chapters = chapterRespository.findAll();
        System.out.println("chapters: " + chapters.get(1).getNumber());
        model.addAttribute("chapters", chapters);
        model.addAttribute("member", memberDto);
        return "main/ww_main";
    }

    @GetMapping("/learn/{chapterId}")
    public String learn(@PathVariable("chapterId") Long chapter_id, Model model) throws SQLException {
        List<Word> words = wordRepository.findAllByChapter_Id(chapter_id);
        model.addAttribute("words", words);
        int wordCount = words.size();
        model.addAttribute("wordTotal", wordCount);
        return"main/ww_learn";
    }

    //인벤토리 api

    @GetMapping("/{name}/inventory") //인벤토리 조회
    public String inventory(@PathVariable("name") String name, Model model) {
        MemberDto memberDto = memberService.findMember(name);
        Long member_id = memberDto.getId();

        Inventory memberInventory= inventoryRepository.findByMember_id(member_id);
        List<Word> words = inventoryService.getInventoryWordList(memberInventory);

        List<Chapter> chapters = chapterRespository.findAll();
        model.addAttribute("chapters", chapters);
        model.addAttribute("words", words);
        model.addAttribute("member", memberDto);

        return "main/inventory";
    }

    @GetMapping("/miniGame")
    public String miniGame(Model model) {
        List<Chapter> chapters = chapterRespository.findAll();
        model.addAttribute("chapters", chapters);
        return"main/miniGame";
    }

    @GetMapping("/tictactoe")
    public String tictactoe() {
        return"main/tictactoe";
    }
}
