package miniJppp.miniProj.domain;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Inventory {

    private int inventory_id;
    private  String inventory_name;
    private int member_id;
    private LocalTime create_at;

    public Inventory(int inventory_id, String inventory_name) {
        this.inventory_id = inventory_id;
        this.inventory_name = inventory_name;
    }
}
