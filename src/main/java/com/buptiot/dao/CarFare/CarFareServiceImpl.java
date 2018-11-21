package com.buptiot.dao.CarFare;

import com.buptiot.pojo.CarFare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Slf4j
@Service
public class CarFareServiceImpl implements CarFareService {

    @Autowired
    CarFareRepository carFareRepository;

    @Override
    public List<CarFare> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return carFareRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findCarFarePageNum(Integer size) {
        log.trace("Executing findCarFarePageNum [{}]", size);
        Integer num = (carFareRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public CarFare findCarFareById(Integer Id) {
        log.trace("Executing findCarFareById [{}]", Id);
        return carFareRepository.findCarFareById(Id);
    }

    @Override
    public List<CarFare> findCarFareByDate(Long Date) {
        log.trace("Executing findCarFareByDate [{}]", Date);
        return carFareRepository.findCarFareByDate(Date);
    }

    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = carFareRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(CarFare carFare) {
        log.trace("Executing save [{}]");
        carFareRepository.save(carFare);
    }

    @Override
    public void update(CarFare carFare) {
        log.trace("Executing update [{}]");
        carFareRepository.update(carFare);
    }

    @Override
    public void deleteById(Integer Id) {
        log.trace("Executing deleteById [{}]",Id);
        carFareRepository.deleteById(Id);
    }

    @Override
    public void deleteByDate(Long Date) {
        log.trace("Executing deleteByDate [{}]",Date);
        carFareRepository.deleteByDate(Date);
    }

    @Override
    public List<CarFare> findAllCarFare() {
        log.trace("Executing findAllWork [{}]");
        return carFareRepository.findAll();
    }
}
