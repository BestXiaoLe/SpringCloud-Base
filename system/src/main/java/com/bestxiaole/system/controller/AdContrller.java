package com.bestxiaole.system.controller;

import com.bestxiaole.server.pojo.Ad;
import com.bestxiaole.server.service.AdService;
import com.bestxiaole.server.utils.ToolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/ad")
public class AdContrller {
    @Autowired
    private AdService adService;

    @RequestMapping("/getlist")
    @ResponseBody
    public List<Ad> getAdList() {
        return adService.getAdList();
    }

}
