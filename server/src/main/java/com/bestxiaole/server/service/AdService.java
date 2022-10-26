package com.bestxiaole.server.service;

import com.bestxiaole.server.pojo.Ad;
import com.bestxiaole.server.utils.ToolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Async
    public void setAdListRedis() {
        System.out.println("开始插入数据...");
        Long begin = System.currentTimeMillis();
        int insertNum = 0;
        Ad ad0 = (Ad) redisTemplate.opsForList().index(ToolEnum.AD_LIST, 0);
        if (null != ad0) {
            insertNum = ad0.getId();
        }
        List<Ad> list = new ArrayList<>();
        for (int i = insertNum + 1; i <= insertNum + 1000000; i++) {
            Ad ad = new Ad();
            String name = (char) (0x4e00 +(int)(Math.random()*(0x9fa5 - 0x4e00 + 1))) +""+(char) (0x4e00 +(int)(Math.random()*(0x9fa5 - 0x4e00 + 1)));
            ad.setId(i);
            ad.setName(name);
            ad.setImgUrl("http://www.cainiaoxiaole.com/"+i+".png");
            ad.setClickUrl("http://www.cainiaoxiaole.com/click_"+i);
            ad.setOrder(0);
            ad.setStatus(1);
            ad.setUpdateTime(new Date());
            ad.setCreateTime(new Date());
            list.add(ad);
        }
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Ad.class));
        redisTemplate.opsForList().leftPushAll(ToolEnum.AD_LIST, list);
        Long end = System.currentTimeMillis();
        System.out.println("插入100W条数据数据完成！");
        System.out.println("耗时 : " + (end - begin) / 1000 + " 秒");
    }
}
