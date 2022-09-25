package com.bestxiaole.server.service;

import com.bestxiaole.server.mapper.BookMapper;
import com.bestxiaole.server.mapper.UserMapper;
import com.bestxiaole.server.pojo.Book;
import com.bestxiaole.server.pojo.BookBuy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;

    public Book getBookById(Book book) {
        return bookMapper.selectByPrimaryKey(book.getId());
    }

    public void updateBookNum(Book book) {
        bookMapper.updateByPrimaryKey(book);
    }
}
