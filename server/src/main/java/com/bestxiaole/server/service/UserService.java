package com.bestxiaole.server.service;

import com.bestxiaole.server.mapper.UserMapper;
import com.bestxiaole.server.pojo.Book;
import com.bestxiaole.server.pojo.BookBuy;
import com.bestxiaole.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private BookService bookService;

    @Resource
    private BookBuyService bookBuyService;

    @Autowired
    private Random random;

    public List<User> list(){
        return userMapper.selectByExample(null);
    }

    public void userBuyBook(User user) {
        Book book = new Book();
        book.setId(2);
        Book book_get = bookService.getBookById(book);

        BookBuy bookBuy = new BookBuy();
        bookBuy.setBookId(book_get.getId());
        bookBuy.setUserId(user.getId());
        bookBuyService.save(bookBuy);

        if (random.nextBoolean()) {
            throw new RuntimeException("抛出错误+++++++++++");
        }

        book_get.setNum(book_get.getNum() - 1);
        bookService.updateBookNum(book_get);
    }
}
