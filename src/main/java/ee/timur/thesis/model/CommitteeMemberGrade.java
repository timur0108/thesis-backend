package ee.timur.thesis.model;

import ee.timur.thesis.model.common.Grade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "committee_member_grade")
public class CommitteeMemberGrade extends Grade {

    @NotNull
    @Column(name = "presentation_score")
    private Integer presentationScore;

    @NotNull
    @Column(name = "visible_to_others")
    private Boolean visibleToOthers;
}
