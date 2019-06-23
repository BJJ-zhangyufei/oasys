package com.buptiot.dao.ReservePlan;

import com.buptiot.pojo.ReservePlan;
import com.buptiot.pojo.user;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
public interface ReservePlanService {

    List<ReservePlan> findALlByPage(Integer page, Integer pageSize);

    Integer findReservePlanPageNum(Integer size);

    ReservePlan findReservePlanById(Integer id);

    ReservePlan findReservePlanByUserId(Integer user_id);

    Integer allWorkCount();

    void save(ReservePlan reservePlan);

    void update(ReservePlan reservePlan);

    void deleteById(Integer id);

    List<ReservePlan> findNoReservePlan();

    List<ReservePlan> findAlreadyReservePlan();

    List<ReservePlan> findCantReservePlan();

    void agree(Integer id);

    void disagree(Integer id);

    void nextBoss(Integer id);

    user findUserInfoByPlanId(Integer id);
}
