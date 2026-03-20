package ee.timur.thesis.model;

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
@Table(name = "supervisor_form")
public class SupervisorForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "context_of_research")
    private String contextOfResearch;

    @Column(name = "student_contribution")
    private String studentContribution;

    @Column(name = "strength_of_thesis")
    private String strengthOfThesis;

    @Column(name = "limitation_of_thesis")
    private String limitationOfThesis;

    @Column(name = "cooperation")
    private String cooperation;

    @Column(name = "additional_comments")
    private String additionalComments;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
