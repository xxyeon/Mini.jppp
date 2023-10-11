package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;

import miniJppp.miniProj.domain.Chapter;
import miniJppp.miniProj.domain.Word;
import miniJppp.miniProj.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/main")
public class WordController {

    private final WordRepository wordRepository;
    @GetMapping
    public String main1(Model model) {
        ArrayList<Chapter> chapters = wordRepository.findAllChapter();
        model.addAttribute("chapters", chapters);
        return "main/ww_main";
    }

    @GetMapping("/learn/{chapterId}")
    public String learn(@PathVariable("chapterId") int chapter_id, Model model) throws SQLException {
        List<Word> words = wordRepository.findByChapter(chapter_id);
        System.out.println(words);
        model.addAttribute("words", words);

        return"main/ww_learn";
    }

    @GetMapping("/inventory")
    public String inventory() {
        return"main/inventory";
    }


}
