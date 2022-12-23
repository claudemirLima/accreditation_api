package com.accreditation.repository;

import com.accreditation.domain.Accreditation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AccreditationRepository extends JpaRepository<Accreditation, UUID> {

    @Query(value = "SELECT EXISTS (\n" +
            "       SELECT * FROM ACCREDITATION_STORY EXT INNER JOIN ACCREDITATION ACC ON  ACC.ACCREDITATION_ID = EXT.ACCREDITATION_ID AND\n" +
            "       ACC.USER_ID = :userID" +
            "       WHERE EXT.REQUEST_DATE = (\n" +
            "        \tSELECT MAX(REQUEST_DATE) FROM ACCREDITATION_STORY WHERE ACCREDITATION_ID  = EXT.ACCREDITATION_ID)\n" +
            "        AND EXT.REQUEST_STATE = 'PENDING')" ,nativeQuery = true)
    boolean existAccreditationPending(String userID);

    List<Accreditation> findAllByUserID(UUID userID);


}
