package com.sd.his.repositories;

import com.sd.his.model.ICDCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICDCodeRepository extends JpaRepository<ICDCode, Long> {

    //ICDCode findByCodeVersionAndDeletedFalse(String codeVersion);

    @Query("SELECT icd FROM ICDCode icd INNER JOIN icd.icdCodes icv INNER JOIN icv.version ver WHERE icd.status = TRUE AND icd.deleted = FALSE")
    List<ICDCode> findAllICDs(Pageable pageable);

    List<ICDCode> findAllByDeletedFalse(Pageable pageable);

    List<ICDCode> findAllByDeletedFalse();

    List<ICDCode> findAllByCodeContainingAndDeletedFalse(String code, Pageable pageable);

    List<ICDCode> findAllByCodeContainingAndDeletedFalse(String code);

    ICDCode findByCode(String iCDCode);

    ICDCode findByCodeAndIdNot(String iCDCode, long iCDCodeId);

}

