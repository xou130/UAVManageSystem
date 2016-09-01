package com.lug.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zzs on 2016/9/1.
 */
@Entity
@Table(name = "uav")
public class Uav {

    @Id
    private String id;

    private User owner;
    private String group;

}
