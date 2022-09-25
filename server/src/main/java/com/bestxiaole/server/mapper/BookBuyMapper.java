package com.bestxiaole.server.mapper;

import com.bestxiaole.server.pojo.BookBuy;
import com.bestxiaole.server.pojo.BookBuyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookBuyMapper {
    long countByExample(BookBuyExample example);

    int deleteByExample(BookBuyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookBuy record);

    int insertSelective(BookBuy record);

    List<BookBuy> selectByExample(BookBuyExample example);

    BookBuy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookBuy record, @Param("example") BookBuyExample example);

    int updateByExample(@Param("record") BookBuy record, @Param("example") BookBuyExample example);

    int updateByPrimaryKeySelective(BookBuy record);

    int updateByPrimaryKey(BookBuy record);
}