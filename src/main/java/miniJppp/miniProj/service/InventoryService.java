package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Learn;
import miniJppp.miniProj.entity.Word;
import miniJppp.miniProj.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

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
