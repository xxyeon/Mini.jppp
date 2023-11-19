package miniJppp.miniProj.domain;

import lombok.Data;

@Data
public class Chapter {

    private int chapter_id;
    private String title;
    private String number;

    public Chapter(int chapter_id, String title, String number) {
        this.chapter_id = chapter_id;
        this.title = title;
        this.number = number;
    }
}
