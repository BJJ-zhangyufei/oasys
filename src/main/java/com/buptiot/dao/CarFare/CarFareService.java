package com.buptiot.dao.CarFare;

/**
 * Created by zyf on 2018/11/15.
 */

import com.buptiot.pojo.CarFare;

import java.util.List;


/**
 * Created by zyf on 2018/11/15.
 */
public interface CarFareService {

    List<CarFare> findALlByPage(Integer page, Integer pageSize);

    Integer findCarFarePageNum(Integer size);

    CarFare findCarFareById(Integer Id);

    List<CarFare> findCarFareByDate(Long Date);

    Integer allWorkCount();

    void save(CarFare carFare);

    void update(CarFare carFare);

    void deleteById(Integer Id);

    void deleteByDate(Long Date);

    List<CarFare> findAllCarFare();
}
