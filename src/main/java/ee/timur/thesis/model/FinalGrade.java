package ee.timur.thesis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "final_grade")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalGrade {

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

    @Column(name = "presentation_score")
    @NotNull
    private Integer presentationScore;

    @Column(name = "total_score")
    @NotNull
    private Integer totalScore;

    @Column(name = "letter_grade", length = 1)
    private String letterGrade;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
