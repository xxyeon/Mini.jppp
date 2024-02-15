package miniJppp.miniProj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "inventory")
    private List<Learn> learnList = new ArrayList<>();

    public Inventory(LocalDateTime createAt, Member member) {
        this.createAt = createAt;
        this.member = member;
    }

    public Inventory() {
    }
}
