package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;

import miniJppp.miniProj.domain.Chapter;
import miniJppp.miniProj.domain.Inventory;
import miniJppp.miniProj.domain.Member;
import miniJppp.miniProj.domain.Word;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.UserRepository;
import miniJppp.miniProj.repository.WordRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static miniJppp.miniProj.repository.InventoryRepository.INVENTORY;

@RequiredArgsConstructor
@Controller
@RequestMapping("/main")
public class WordController {

    private final WordRepository wordRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userReposiotry;


    @GetMapping("/{name}")
    public String main1(@PathVariable String name, Model model) {
        Member member = userReposiotry.findUser(name);
        System.out.println("member = " + member);
        if(member.getName() == null){
            userReposiotry.save(name);
            Member newmember = userReposiotry.findUser(name);
            int member_id = newmember.getMember_id();
            inventoryRepository.create(member_id);
        }
        Member newmember = userReposiotry.findUser(name);
         //인벤토리 생성 후 (인벤토리 이름 name+'인벤토리')
//        Inventory inventory = inventoryRepository.findByName(name+INVENTORY);
//        userReposiotry.updateMemberInventoroy(inventory.getInventory_id()); //member 테이블에 인벤토리 fk 설정

        ArrayList<Chapter> chapters = wordRepository.findAllChapter();
        model.addAttribute("chapters", chapters);
        model.addAttribute("member", newmember);
        return "main/ww_main";
    }

    @GetMapping("/learn/{chapterId}")
    public String learn(@PathVariable("chapterId") int chapter_id, Model model) throws SQLException {
        List<Word> words = wordRepository.findByChapter(chapter_id);
        model.addAttribute("words", words);
        int wordCount = wordRepository.wordTotal();
        model.addAttribute("wordTotal", wordCount);
        return"main/ww_learn";
    }

    //인벤토리 api

    @GetMapping("/{name}/inventory") //인벤토리 조회
    public String inventory(@PathVariable("name") String name, Model model) {
        int member_id = userReposiotry.findUser(name).getMember_id();
        List<Integer> wordIdArray = inventoryRepository.findById(member_id);
        ArrayList<Word> words = wordRepository.findById(wordIdArray);
        ArrayList<Chapter> chapters = wordRepository.findAllChapter();
        model.addAttribute("chapters", chapters);

        return "main/inventory";
    }

    @GetMapping("/miniGame")
    public String miniGame(Model model) {
        ArrayList<Chapter> chapters = wordRepository.findAllChapter();
        model.addAttribute("chapters", chapters);
        return"main/miniGame";
    }

    @GetMapping("/tictactoe")
    public String tictactoe() {
        return"main/tictactoe";
    }
}
