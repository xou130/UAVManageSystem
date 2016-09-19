package com.lug.service;

import com.lug.model.Location;
import com.lug.model.Uav;
import com.lug.repository.LocationRepository;
import com.lug.repository.UavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zzs on 2016/9/17.
 */
@Service
public class UavService {

    private UavRepository uavRepository;
    private LocationRepository locationRepository;

    @Autowired
    public UavService(UavRepository uavRepository, LocationRepository locationRepository) {
        this.uavRepository = uavRepository;
        this.locationRepository = locationRepository;
    }

    @Transactional
    public List<Location> getLocations(String uuid){
        Long uavId = uavRepository.findByUuid(uuid).getId();
        return locationRepository.findByUavId(uavId);
    }

    @Transactional
    public boolean addLocation(String uuid, double latitude, double longitude, double height){
        try{
            Uav uav = uavRepository.findByUuid(uuid);
            if (uav != null){
                Location location = new Location(uav.getId(), new Date(), latitude, longitude, height);
                locationRepository.save(location);
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public Uav getUavDetail(String uuid){
        Uav uav = uavRepository.findByUuid(uuid);
        return uav;
    }

    public Long getUavBelong(String uuid){
        Uav uav = uavRepository.findByUuid(uuid);
        return uav.getUser_id();
    }

    public void delUav(Long userId, String uuid){
        Uav uav = uavRepository.findByUuid(uuid);
        uavRepository.delete(uav);
    }




}
