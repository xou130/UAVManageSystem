package com.lug.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zzs on 2016/9/1.
 */
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;


}
