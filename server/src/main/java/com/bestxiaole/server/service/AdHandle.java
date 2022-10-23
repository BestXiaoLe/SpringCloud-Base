package com.bestxiaole.server.service;

import com.bestxiaole.server.pojo.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable("ad")
public class AdHandle implements EntryHandler<Ad> {

    @Autowired
    private AdService adService;

    @Override
    public void insert(Ad ad) {
        adService.modify(ad);
    }

    @Override
    public void update(Ad before, Ad after) {
        adService.modify(after);
    }

    @Override
    public void delete(Ad ad) {
        adService.delete(ad.getId());
    }
}
