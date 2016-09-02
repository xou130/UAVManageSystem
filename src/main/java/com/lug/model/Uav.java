package com.lug.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zzs on 2016/9/2.
 */
@Entity
@Table(name = "uav")
public class Uav implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;
    private User userByUserId;
    private String groupName;
    private String info;
    private Date registDate;


    public Uav(){}

    public Uav(String uuid, User userByUserId, String groupName, String info, Date registDate) {
        this.uuid = uuid;
        this.userByUserId = userByUserId;
        this.groupName = groupName;
        this.info = info;
        this.registDate = registDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "Uav{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", userByUserId=" + userByUserId +
                ", groupName='" + groupName + '\'' +
                ", info='" + info + '\'' +
                ", registDate=" + registDate +
                '}';
    }
}
