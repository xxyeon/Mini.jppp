package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;

import miniJppp.miniProj.DTO.LearnDto;
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
    private final BookMarkRepository bookMarkRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/learn/{chapterId}")
    public String learn(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("chapterId") Long chapter_id, Model model) throws SQLException {
        System.out.println(principalDetails.getUser().getEmail());
        Member member = principalDetails.getUser();
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .nickname(member.getName())
                .email(member.getEmail())
                .profileImgUrl(member.getProfileImgUrl()).build();
        List<Word> words = wordRepository.findAllByChapter_Id(chapter_id);
        model.addAttribute("words", words);
        int wordCount = words.size();
        model.addAttribute("wordTotal", wordCount);
        model.addAttribute("member",memberDto);
        return"/main/ww_learn";
    }

    //인벤토리 api

    @GetMapping("/inventory") //인벤토리 조회
    public String inventory(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        Member member = principalDetails.getUser();
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .nickname(member.getName())
                .email(member.getEmail())
                .profileImgUrl(member.getProfileImgUrl()).build();
        System.out.println(member);
        Inventory memberInventory= inventoryService.getInventoryByName(memberDto.getNickname());
        List<Word> words = inventoryService.getInventoryWordList(memberInventory);
        List<Learn> learns = learnRepository.findAllByInventory(memberInventory);
        BookMark memberBookMark = bookMarkRepository.findBookMarkByMember(member);
        List<Review> memberReviews = reviewRepository.findAllByBookMark(memberBookMark);

        List<Chapter> chapters = chapterRespository.findAll();

        model.addAttribute("chapters", chapters);
        model.addAttribute("words", words);
        model.addAttribute("learns", learns);
        model.addAttribute("member",memberDto);
        model.addAttribute("reviews", memberReviews);

        return "/main/inventory";
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
    public String profile(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        Member member = principalDetails.getUser();
        Member member = memberRepository.findByProviderAndEmail(principalDetails.getUser().getProvider(), principalDetails.getUser().getEmail()); // 회원정보가 수정되면 DB에서 가져와야함. 세션? 에서 가져오면 변경 전 데이터를 그대로 가지고 있기때문에 변경 사항이 적용이 안된다
        MemberDto memberDto = MemberDto.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .updateAt(member.getUpdateAt())
                .provider(member.getProvider())
                .isPW(member.isPw())
                .build();
        model.addAttribute("member", memberDto);
        return"/main/profile";
    }

    //ajax컨트롤러
    @GetMapping("/get-bookmark-data")
    public String getBookmarkData(@RequestParam(name="nickname") String nickname, Model model) {
        System.out.println(nickname);
        model.addAttribute("reviews", inventoryService.getReviewData(nickname));
        return "/main/inventory::#bookmarkDiv";
    }
}
