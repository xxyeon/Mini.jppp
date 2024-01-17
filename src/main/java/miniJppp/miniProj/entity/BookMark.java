package miniJppp.miniProj.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK_MARK")
@Getter
public class BookMark { //쿼리 예약어와 겹칩
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_mark_id")
    private Long id;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
