package com.buptiot.dao.Travel;

import com.buptiot.dao.Travel.TravelRepository;
import com.buptiot.dao.Travel.TravelService;
import com.buptiot.pojo.Travel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Slf4j
@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Override
    public List<Travel> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return travelRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findTravelPageNum(Integer size) {
        log.trace("Executing findTravelPageNum [{}]", size);
        Integer num = (travelRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public Travel findTravelByTravelId(Integer travelId) {
        log.trace("Executing findTravelById [{}]", travelId);
        return travelRepository.findTravelByTravelId(travelId);
    }

    @Override
    public List<Travel> findTravelByDate(Long Date) {
        log.trace("Executing findTravelByDate [{}]", Date);
        return travelRepository.findTravelByDate(Date);
    }

    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = travelRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(Travel travel) {
        log.trace("Executing save [{}]");
        travelRepository.save(travel);
    }

    @Override
    public void update(Travel travel) {
        log.trace("Executing update [{}]");
        travelRepository.update(travel);
    }

    @Override
    public void deleteByTravelId(Integer travelId) {
        log.trace("Executing deleteById [{}]",travelId);
        travelRepository.deleteByTravelId(travelId);
    }

    @Override
    public void deleteByDate(Long Date) {
        log.trace("Executing deleteByDate [{}]",Date);
        travelRepository.deleteByDate(Date);
    }

    @Override
    public List<Travel> findAllTravel() {
        log.trace("Executing findAllWork [{}]");
        return travelRepository.findAll();
    }


}
