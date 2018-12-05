package com.buptiot.dao.ReservePlan;

import com.buptiot.pojo.ReservePlan;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
public interface ReservePlanService {

    List<ReservePlan> findALlByPage(Integer page, Integer pageSize);

    Integer findReservePlanPageNum(Integer size);

    ReservePlan findReservePlanById(Integer id);

    Integer allWorkCount();

    void save(ReservePlan reservePlan);

    void update(ReservePlan reservePlan);

    void deleteById(Integer id);

    List<ReservePlan> findAllReservePlan();
}
