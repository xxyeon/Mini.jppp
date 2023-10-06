package mini.miniProj.repository;

import mini.miniProj.domain.Inventory;
import mini.miniProj.domain.Learn;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository {

    private final List<Inventory> inventories = new ArrayList<>();

    public List<Inventory> findAll() {
        return inventories;
    }

    public void save(Learn learn, Inventory inventory) {

    }

    public void deleteById(Learn learn, Inventory inventory) {

    }
}
