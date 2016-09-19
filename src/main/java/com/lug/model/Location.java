package com.lug.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.Id;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by zzs on 2016/9/6.
 */
public class Location {

    @Id
    private String id;

    private Long uavId;
    private Date date;
    private double latitude;
    private double longitude;
    private double height;

//    @Transient
//    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public Location(Long uavId, Date date, double latitude, double longitude, double height){
        this.uavId = uavId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
    }

    public Location() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUavId() {
        return uavId;
    }

    public void setUavId(Long uavId) {
        this.uavId = uavId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
