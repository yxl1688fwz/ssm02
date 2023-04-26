package com.ssm.mapper;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Book;

import java.util.List;

public interface BookMapper {
    List<Book> getAllBook();

    Book getBookById(Integer id);

    int updateBook(Book book);

    List<Book> searchBook(Book book);

    void addBook(Book book);

    List<Book> searchMyBorrow(Book book);

    List<Book> searchBorrow(Book book);
}
