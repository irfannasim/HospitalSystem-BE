package com.sd.his.repositiories;


import com.sd.his.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);

    User findById(long id);

    User findByUsernameOrEmailAndActiveTrueAndDeletedFalse(String username, String email);

}
