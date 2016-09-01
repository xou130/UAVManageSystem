package com.lug.repository;

import com.lug.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zzs on 2016/9/1.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
