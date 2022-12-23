package com.accreditation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "DOCUMENT")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOCUMENT_ID")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MINE_TYPE")
    private String mimeType;
    @Lob
    @Column(name = "FILE")
    private byte content[];

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCREDITATION_ID",referencedColumnName = "ACCREDITATION_ID")
    private Accreditation accreditation;
}
