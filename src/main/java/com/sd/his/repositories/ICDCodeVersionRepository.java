package com.sd.his.repositories;

import com.sd.his.model.ICDCodeVersion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICDCodeVersionRepository extends JpaRepository<ICDCodeVersion, Long> {

    List<ICDCodeVersion> findAllByOrderByVersion_name(Pageable pageable);
    List<ICDCodeVersion> findAllByOrderByVersion_name();

    List<ICDCodeVersion> findAllByVersion_idAndVersion_deletedFalseAndIcd_DeletedFalse(long iCDCVsById);

    List<Long> deleteAllByVersion_id(long id);


    List<ICDCodeVersion> findAllByVersion_NameContainingAndVersion_DeletedFalseOrIcd_CodeContainingAndIcd_DeletedFalse(String versionName,String code, Pageable pageable);
    List<ICDCodeVersion> findAllByVersion_NameContainingAndVersion_DeletedFalseOrIcd_CodeContainingAndIcd_DeletedFalse(String versionName,String code);

}
