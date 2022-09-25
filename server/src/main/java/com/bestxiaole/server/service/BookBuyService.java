package com.bestxiaole.server.service;

import com.bestxiaole.server.mapper.BookBuyMapper;
import com.bestxiaole.server.mapper.BookMapper;
import com.bestxiaole.server.pojo.BookBuy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookBuyService {
    @Resource
    private BookBuyMapper bookBuyMapper;

    public void save(BookBuy bookBuy) {
        bookBuyMapper.insert(bookBuy);
    }
}
