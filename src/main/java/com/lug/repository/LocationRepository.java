package com.lug.repository;

import com.lug.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zzs on 2016/9/6.
 */
@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

    public List<Location> findByUavId(Long uavId);

//    public List<Location> findByDateBetween(Date startTime, Date endTime);
    //{"time":{"$gt": startTime,"$lt": endTime}}

}
