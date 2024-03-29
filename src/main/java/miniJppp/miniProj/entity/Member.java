package miniJppp.miniProj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Setter
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

    private LocalDateTime updateAt;

    private String email;
    private String password;
    private String provider;
    private String providerId;
    @ColumnDefault("true")
    private boolean isPw;

    public Member(String name, String profileImgUrl, LocalDateTime createAt) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.createAt = createAt;
    }

    public Member() {
    }
}
