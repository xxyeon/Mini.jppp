package miniJppp.miniProj.entity;


import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

@Entity
@Getter
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    @Column
    private String word;

    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
