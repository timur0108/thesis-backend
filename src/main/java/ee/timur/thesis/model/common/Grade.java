package ee.timur.thesis.model.common;

import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
public class Grade {

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
