package miniJppp.miniProj.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private Long id;

    @Column
    private String title;
}
