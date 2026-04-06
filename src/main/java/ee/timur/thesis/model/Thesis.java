package ee.timur.thesis.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thesis")
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "student_name")
    private String studentName;

    @NotNull
    @Column(name = "level_of_studies")
    private String levelOfStudies;

    @NotNull
    @Column(name = "curriculum")
    private String curriculum;

    @NotNull
    @Column(name = "language_of_thesis")
    private String languageOfThesis;

    @NotNull
    @Column(name = "volume_ects")
    private Integer volumeEcts;

    @NotNull
    @Column(name = "title_estonian")
    private String titleEstonian;

    @NotNull
    @Column(name = "title_english")
    private String titleEnglish;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id")
    private Session session;

    @OneToMany(mappedBy = "thesis", fetch = FetchType.LAZY)
    private List<ThesisUserRole> thesisUserRoles;

    @OneToOne(mappedBy = "thesis", fetch = FetchType.LAZY)
    private FinalGrade finalGrade;
}
