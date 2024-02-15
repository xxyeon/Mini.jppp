package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.Learn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnRepository extends JpaRepository<Learn, Long> {

    public Learn findByInventory_Id(Long inventoryId);
}
