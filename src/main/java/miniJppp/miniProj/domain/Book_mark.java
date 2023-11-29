package miniJppp.miniProj.domain;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Book_mark {

    private int book_mark_id;
    private LocalTime create_at;
    private int member_id;
}
