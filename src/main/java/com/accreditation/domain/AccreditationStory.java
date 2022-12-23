package com.accreditation.domain;

import com.accreditation.type.AccreditationStatus;
import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCREDITATION_STORY")
public class AccreditationStory {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "ACCREDITATION_STORY_ID")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ACCREDITATION_ID",referencedColumnName = "ACCREDITATION_ID" )
    private Accreditation accreditation;

    @Column(name = "REQUEST_DATE")
    private LocalDateTime request;

    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_STATE")
    private AccreditationStatus status;

}
