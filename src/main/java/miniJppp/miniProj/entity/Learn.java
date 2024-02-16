package miniJppp.miniProj.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
