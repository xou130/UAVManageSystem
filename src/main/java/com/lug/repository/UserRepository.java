package com.lug.repository;

import com.lug.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zzs on 2016/9/1.
 */
@Repository
public interface UserPesitory extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=:qUsername")
    User findByUsername(@Param("qUsername") String username);
}
