package com.accreditation.domain;

import com.accreditation.config.UUIDConverter;
import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.AccreditationType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "ACCREDITATION")
public class Accreditation {

    @Id
    @Column(name = "ACCREDITATION_ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private AccreditationType type;

    @Column(name = "USER_ID")
    @Convert(converter = UUIDConverter.class)
    private UUID userID;

    @OneToMany(mappedBy = "accreditation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();

    @OneToMany(mappedBy = "accreditation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccreditationStory> accreditationStories = new ArrayList<>();

}
