package com.accreditation.repository;

import com.accreditation.domain.AccreditationStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AccreditationStoryRepository extends JpaRepository<AccreditationStory, UUID> {

    @Query(value = "SELECT * FROM ACCREDITATION_STORY ACC WHERE ACC.ACCREDITATION_ID = :accreditationId",nativeQuery = true)
    List<AccreditationStory> findByAccreditationID(String accreditationId);

    @Query(value = "SELECT\n" +
            "            EXT \n" +
            "        FROM\n" +
            "            ACCREDITATION_STORY EXT \n" +
            "        INNER JOIN\n" +
            "            ACCREDITATION ACC \n" +
            "                ON  ACC.ACCREDITATION_ID = EXT.ACCREDITATION_ID\n" +
            "        WHERE\n" +
            "            EXT.REQUEST_DATE = (\n" +
            "                SELECT\n" +
            "                    MAX(REQUEST_DATE) \n" +
            "                FROM\n" +
            "                    ACCREDITATION_STORY \n" +
            "                WHERE\n" +
            "                    ACCREDITATION_ID  = EXT.ACCREDITATION_ID\n" +
            "            )         \n" +
            "            AND EXT.REQUEST_STATE = 'PENDING'\n" +
            "            AND EXT.REQUEST_DATE >= :localDate",nativeQuery = true)
    List<AccreditationStory> findAccreditationOutOfDate(LocalDate localDate);
}
