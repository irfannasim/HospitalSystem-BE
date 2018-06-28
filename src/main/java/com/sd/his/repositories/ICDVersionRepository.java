package com.sd.his.repositories;

import com.sd.his.model.ICDVersion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ICDVersionRepository extends JpaRepository<ICDVersion,Long> {

    ICDVersion findByNameAndDeletedFalse(String iCDVersionName);

    List<ICDVersion> findAllByDeletedFalse(Pageable pageable);
    List<ICDVersion> findAllByDeletedFalse();

    List<ICDVersion> findAllByNameContainingAndDeletedFalse(String name, Pageable pageable);
    List<ICDVersion> findAllByNameContainingAndDeletedFalse(String name);

    ICDVersion findByNameAndDeletedFalseAndIdNot(String name, long id);

}
