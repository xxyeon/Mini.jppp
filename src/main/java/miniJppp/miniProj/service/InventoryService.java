package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.DTO.LearnDto;
import miniJppp.miniProj.DTO.ReviewDto;
import miniJppp.miniProj.entity.*;
import miniJppp.miniProj.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final MemberRepository memberRepository;
    private final WordRepository wordRepository;
    private final LearnRepository learnRepository;
    private final BookMarkRepository bookMarkRepository;
    private final ReviewRepository reviewRepository;

    public Inventory getInventoryByName(String name) {
        Member findMember = memberRepository.findByName(name);
        return inventoryRepository.findByMember(findMember);
    }

    @Transactional
    public List<Word> getInventoryWordList(Inventory inventory) {
        List<Learn> learnList = inventory.getLearnList();
        ArrayList<Word> wordList = new ArrayList<>();
        for (int i = 0; i < learnList.size(); i++) {
            wordList.add(learnList.get(i).getWord());
        }
        return wordList;
    }

    @Transactional
    public void saveLearnData(LearnDto learnDto) {
        Word word = wordRepository.findWordById(Long.valueOf(learnDto.getWordId()));
        Inventory inventory = inventoryRepository.findByMember(memberRepository.findByName(learnDto.getNickname()));
        Learn learn = Learn.builder()
                .word(word)
                .learn(learnDto.isLearn())
                .inventory(inventory).build();

        Learn findlearn = learnRepository.findByWordAndInventory(word, inventory);
        if (findlearn != null) {
            log.info("이미 존재하는 단어입니다.");
        } else {
            learnRepository.save(learn);
        }
    }

    @Transactional
    public void saveReviewData(ReviewDto reviewDto) {
        BookMark findBookmark = bookMarkRepository.findBookMarkByMember(memberRepository.findByName(reviewDto.getNickname()));
        Word findWord = wordRepository.findWordById(Long.valueOf(reviewDto.getWordId()));
        Review findReview = reviewRepository.findByBookMarkAndWord(findBookmark, findWord);
        if (findReview != null) {
            log.info("북마크에 이미 존재하는 단어입니다.");
        } else {
            Review review = Review.builder()
                    .bookMark(findBookmark)
                    .word(findWord).build();
            reviewRepository.save(review);
        }

    }

    public void deleteReviewData(ReviewDto reviewDto) {
        BookMark findBookmark = bookMarkRepository.findBookMarkByMember(memberRepository.findByName(reviewDto.getNickname()));
        Word findWord = wordRepository.findWordById(Long.valueOf(reviewDto.getWordId()));
        Review findReview = reviewRepository.findByBookMarkAndWord(findBookmark, findWord);
        reviewRepository.deleteById(findReview.getId());
    }
}
