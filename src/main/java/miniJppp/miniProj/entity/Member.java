package miniJppp.miniProj.entity;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.awt.font.TextHitInfo;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String profileImgUrl;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public Member(String name, String profileImgUrl, LocalDateTime createAt) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.createAt = createAt;
    }

    public Member() {
    }
}
