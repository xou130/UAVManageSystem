package com.lug.repository;

import com.lug.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zzs on 2016/9/1.
 */
public interface UserPesitory extends JpaRepository<User, Long> {
}
