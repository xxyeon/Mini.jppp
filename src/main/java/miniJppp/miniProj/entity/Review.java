package miniJppp.miniProj.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_mark_id")
    private BookMark bookMark;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
}
