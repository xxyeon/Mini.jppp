package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Learn;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.entity.Word;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import miniJppp.miniProj.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final MemberRepository memberRepository;

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
}
