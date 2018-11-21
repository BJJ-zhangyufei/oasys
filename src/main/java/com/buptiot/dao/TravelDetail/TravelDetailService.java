package com.buptiot.dao.TravelDetail;

import com.buptiot.pojo.TravelDetail;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */
public interface TravelDetailService {

    List<TravelDetail> findALlByPage(Integer page, Integer pageSize);

    Integer findTravelDetailPageNum(Integer size);

    TravelDetail findTravelDetailByDetailId(Integer detailId);

    List<TravelDetail> findTravelDetailByStartDate(Long startDate);

    List<TravelDetail> findTravelDetailByEndDate(Long endDate);

    Integer allWorkCount();

    void save(TravelDetail travelDetail);

    void update(TravelDetail travelDetail);

    void deleteByDetailId(Integer detailId);

    void deleteByStartDate(Long startDate);

    void deleteByEndDate(Long endDate);

    List<TravelDetail> findAllTravelDetail();

}
