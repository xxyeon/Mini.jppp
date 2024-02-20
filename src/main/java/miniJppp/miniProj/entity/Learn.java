package miniJppp.miniProj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniJppp.miniProj.repository.LearnRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Learn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learn_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    private boolean learn;

}
