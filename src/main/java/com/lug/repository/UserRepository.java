package com.lug.repository;

import com.lug.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by zzs on 2016/9/1.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=:qUsername")
    User findByUsername(@Param("qUsername") String username);

    @Modifying
    @Query("update User u set u.password=:qPassword where u.id=:qId")
    int updateUserPwd(@Param("qId") Long id, @Param("qPassword") String password);

    /*
    需要补完
     */
    @Modifying
    @Query("update User u set u.email=:qEmail where u.id=:qId")
    int updateUserInfo(@Param("qId") Long id, @Param("qEmail") String email,
                       @Param("qAddress") String address);
}
