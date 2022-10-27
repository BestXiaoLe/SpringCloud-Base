package com.bestxiaole.server.service;

import com.bestxiaole.server.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ElasticsearchRestService {

    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private Random random;

    private String getAddr() {
        StringBuilder addr = new StringBuilder((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))) + "");
        for (int i = 0; i < 50; i++) {
            addr.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
        }
        return addr.toString();
    }

    private String getName() {
        StringBuilder name = new StringBuilder((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))) + "");
        name.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
        return name.toString();
    }

    @Async
    public void setListEs() throws InterruptedException {
        System.out.println("开始插入数据...");
        Long begin = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            List<UserInfo> list = new ArrayList<>();
            for (int j = 1; j <= 10000; j++) {
                String id = UUID.randomUUID().toString();
                Date date = new Date();
                list.add(new UserInfo(id, getName(), getAddr(), date));
            }
            template.save(list);
            System.out.println("插入第" + i + "W条数据完成！");
        }
        Long end = System.currentTimeMillis();
        System.out.println("插入100W条数据完成！");
        System.out.println("耗时 : " + (((end - begin) / 1000)) + " 秒");
    }
}
