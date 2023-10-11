package miniJppp.miniProj.domain;

import lombok.Data;

@Data
public class Word {

    private int word_id;
    private String word;
    private String answer;

    public Word(int word_id, String word, String answer) {
        this.word_id = word_id;
        this.word = word;
        this.answer = answer;
    }
}
