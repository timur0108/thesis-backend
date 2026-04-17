package ee.timur.thesis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
    private BigDecimal contentScore;

    @Column(name = "complexity_score")
    @NotNull
    private BigDecimal complexityScore;

    @Column(name = "appearance_score")
    @NotNull
    private BigDecimal appearanceScore;

    @Column(name = "presentation_score")
    @NotNull
    private BigDecimal presentationScore;

    @Column(name = "total_score")
    @NotNull
    private BigDecimal totalScore;

    @Column(name = "letter_grade", length = 1)
    private String letterGrade;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
