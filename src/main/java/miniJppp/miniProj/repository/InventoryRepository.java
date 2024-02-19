package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    public List<Inventory> findAll();

    public Inventory findByMember_id(Long memberId);

    public Inventory findByMember(Member member);

    public Inventory save(Inventory inventory);

}
