package com.sd.his.repositories;

import com.sd.his.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties,Integer> {

    Properties findById(Integer i);

}
