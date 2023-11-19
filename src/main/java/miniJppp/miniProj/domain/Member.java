package miniJppp.miniProj.domain;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Member {

    private int member_id;
    private String name;
    private String profileImgUrl;
    private LocalTime create_at;
    private int inventory_id;
    private int book_mark_id;

}
