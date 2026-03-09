package ee.timur.thesis.model.common;

import ee.timur.thesis.model.Thesis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_score")
    @NotNull
    private Integer contentScore;

    @Column(name = "complexity_score")
    @NotNull
    private Integer complexityScore;


    @Column(name = "appearance_score")
    @NotNull
    private Integer appearanceScore;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
