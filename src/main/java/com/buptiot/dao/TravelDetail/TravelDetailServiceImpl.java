package com.buptiot.dao.TravelDetail;

import com.buptiot.pojo.TravelDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Slf4j
@Service
public class TravelDetailServiceImpl implements TravelDetailService{

    @Autowired
    TravelDetailRepository travelDetailRepository;

    @Override
    public List<TravelDetail> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return travelDetailRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findTravelDetailPageNum(Integer size) {
        log.trace("Executing findTravelPageNum [{}]", size);
        Integer num = (travelDetailRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public TravelDetail findTravelDetailByDetailId(Integer detailId) {
        log.trace("Executing findTravelById [{}]", detailId);
        return travelDetailRepository.findTravelDetailByDetailId(detailId);
    }

    @Override
    public List<TravelDetail> findTravelDetailByStartDate(Long startDate) {
        log.trace("Executing findTravelByDate [{}]", startDate);
        return travelDetailRepository.findTravelDetailByStartDate(startDate);
    }

    @Override
    public List<TravelDetail> findTravelDetailByEndDate(Long endDate) {
        log.trace("Executing findTravelByDate [{}]", endDate);
        return travelDetailRepository.findTravelDetailByEndDate(endDate);
    }

    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = travelDetailRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(TravelDetail travelDetail) {
        log.trace("Executing save [{}]");
        travelDetailRepository.save(travelDetail);
    }

    @Override
    public void update(TravelDetail travelDetail) {
        log.trace("Executing update [{}]");
        travelDetailRepository.update(travelDetail);
    }

    @Override
    public void deleteByDetailId(Integer detailId) {
        log.trace("Executing deleteById [{}]",detailId);
        travelDetailRepository.deleteByDetailId(detailId);
    }

    @Override
    public void deleteByStartDate(Long startDate) {
        log.trace("Executing deleteByDate [{}]",startDate);
        travelDetailRepository.deleteByStartDate(startDate);
    }

    @Override
    public void deleteByEndDate(Long endDate) {
        log.trace("Executing deleteByDate [{}]",endDate);
        travelDetailRepository.deleteByEndDate(endDate);
    }

    @Override
    public List<TravelDetail> findAllTravelDetail() {
        log.trace("Executing findAllWork [{}]");
        return travelDetailRepository.findAll();
    }

}
