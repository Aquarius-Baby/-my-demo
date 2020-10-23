package com.example.rocketmqlearn.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 2:49 下午
 * @Description:
 */
@Service
public class ParamConfigService {

    @Value("${plat.plat-group}")
    public String platGroup;
    @Value("${plat.plat-topic}")
    public String platTopic;
    @Value("${plat.plat-tag}")
    public String accountTag;

    public String getPlatGroup() {
        return platGroup;
    }

    public void setPlatGroup(String platGroup) {
        this.platGroup = platGroup;
    }

    public String getPlatTopic() {
        return platTopic;
    }

    public void setPlatTopic(String platTopic) {
        this.platTopic = platTopic;
    }

    public String getAccountTag() {
        return accountTag;
    }

    public void setAccountTag(String accountTag) {
        this.accountTag = accountTag;
    }
}