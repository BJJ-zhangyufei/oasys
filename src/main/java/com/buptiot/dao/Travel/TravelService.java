package com.buptiot.dao.Travel;

import com.buptiot.pojo.Travel;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */
public interface TravelService {

    List<Travel> findALlByPage(Integer page, Integer pageSize);

    Integer findTravelPageNum(Integer size);

    Travel findTravelByTravelId(Integer travelId);

    List<Travel> findTravelByDate(Long Date);

    Integer allWorkCount();

    void save(Travel travel);

    void update(Travel travel);

    void deleteByTravelId(Integer travelId);

    void deleteByDate(Long Date);

    List<Travel> findAllTravel();
}
