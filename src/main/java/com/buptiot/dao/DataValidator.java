package com.buptiot.dao;

import com.buptiot.pojo.IdBased;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyf on 2018/11/16.
 */
@Slf4j
public abstract class DataValidator<D extends IdBased> {

    public void validate(D data) {
        try {
            if (data == null) {
                throw new DataValidationException("Data object can't be null!");
            }
            validateDataImpl(data);
            if (data.getId() == null) {
                validateCreate(data);
            } else {
                validateUpdate(data);
            }
        } catch (DataValidationException e) {
            log.error("Data object is invalid: [{}]", e.getMessage());
            throw e;
        }
    }

    protected void validateDataImpl(D data) {
    }

    protected void validateCreate(D data) {
    }

    protected void validateUpdate(D data) {
    }

    protected boolean isSameData(D existentData, D actualData) {
        return actualData.getId() != null && existentData.getId().equals(actualData.getId());
    }

    public static void validatePhone(String phone){
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if(!m.matches()){
            throw new DataValidationException("无效的手机号码 '" + phone + "'！");
        }
    }

}
