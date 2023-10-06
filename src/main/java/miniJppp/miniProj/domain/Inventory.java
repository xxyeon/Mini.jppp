package miniJppp.miniProj.domain;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Inventory {

    private int inventory_id;
    private LocalTime create_at;
}
