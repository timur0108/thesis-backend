package ee.timur.thesis.model;

import ee.timur.thesis.model.common.Grade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviewer_grade")
public class ReviewerGrade extends Grade {

    @Column(name = "content_reasoning")
    @NotNull
    private String contentReasoning;

    @Column(name = "complexity_reasoning")
    @NotNull
    private String complexityReasoning;

    @Column(name = "appearance_reasoning")
    @NotNull
    private String appearanceReasoning;

    @Column(name = "evaluation_summary")
    @NotNull
    private String evaluationSummary;

    @Column(name = "questions")
    @NotNull
    private String questions;

}
