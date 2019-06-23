package com.buptiot.dao.ReservePlan;

import com.buptiot.pojo.ReservePlan;
import com.buptiot.pojo.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
@Slf4j
@Service
public class ReservePlanServiceImpl implements ReservePlanService {

    @Autowired
    ReservePlanRepository reservePlanRepository;

    @Override
    public List<ReservePlan> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return reservePlanRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findReservePlanPageNum(Integer size) {
        log.trace("Executing findReservePlanPageNum [{}]", size);
        Integer num = (reservePlanRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public ReservePlan findReservePlanById(Integer id) {
        log.trace("Executing findReservePlanById [{}]", id);
        return reservePlanRepository.findReservePlanById(id);
    }

    @Override
    public ReservePlan findReservePlanByUserId(Integer user_id) {
        log.trace("Executing findReservePlanByUserId [{}]", user_id);
        return reservePlanRepository.findReservePlanByUserId(user_id);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = reservePlanRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(ReservePlan reservePlan) {
        log.trace("Executing save [{}]");
        reservePlanRepository.save(reservePlan);
    }

    @Override
    public void update(ReservePlan reservePlan) {
        log.trace("Executing update [{}]");
        reservePlanRepository.update(reservePlan);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]",id);
        reservePlanRepository.deleteById(id);
    }

    @Override
    public List<ReservePlan> findNoReservePlan() {
        log.trace("Executing findAllReservePlan [{}]");
        return reservePlanRepository.findNo();
    }


    @Override
    public List<ReservePlan> findAlreadyReservePlan() {
        log.trace("Executing findAllReservePlan [{}]");
        return reservePlanRepository.findAlready();
    }

    @Override
    public List<ReservePlan> findCantReservePlan() {
        log.trace("Executing findCantReservePlan [{}]");
        return reservePlanRepository.findCantReservePlan();
    }

    @Override
    public void agree(Integer id) {
        log.trace("Executing agree [{}]");
        reservePlanRepository.agree(id);
    }

    @Override
    public void disagree(Integer id) {
        log.trace("Executing disagree [{}]");
        reservePlanRepository.disagree(id);
    }

    @Override
    public void nextBoss(Integer id) {
        log.trace("Executing nextBoss [{}]");
        reservePlanRepository.nextBoss(id);
    }

    @Override
    public user findUserInfoByPlanId(Integer id) {
        log.trace("Executing findUserInfoByPlanId [{}]", id);
        return reservePlanRepository.findUserInfoByPlanId(id);
    }

}
