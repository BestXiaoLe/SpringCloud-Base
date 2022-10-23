package com.bestxiaole.server.service;

import com.bestxiaole.server.pojo.Ad;
import com.bestxiaole.server.utils.ToolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {
    @Autowired
    private RedisTemplate redisTemplate;

    public List<Ad> getAdList() {
        List<Ad> list = redisTemplate.boundHashOps(ToolEnum.AD_LIST).values();
        return list;
    }

    public void modify(Ad ad) {
        redisTemplate.boundHashOps(ToolEnum.AD_LIST).put(ad.getId(), ad);
    }

    public void delete(Integer id) {
        redisTemplate.boundHashOps(ToolEnum.AD_LIST).delete(id);
    }
}
