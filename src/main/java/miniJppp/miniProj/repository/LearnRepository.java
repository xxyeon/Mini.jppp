package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Learn;
import miniJppp.miniProj.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearnRepository extends JpaRepository<Learn, Long> {

    public List<Learn> findAllByInventory(Inventory inventory);

    public Learn findByWordAndInventory(Word word, Inventory inventory);
}
