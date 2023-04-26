package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Book;

public interface BookService {
    /**
     * 分页查询新图书
     * @return
     */
    PageInfo<Book> selectNewBooks(Integer pageNum);

    /**
     * 更具id查询图书
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 借阅图书
     * @param book
     * @return
     */
    Integer borrowBook(Book book);

    /**
     * 分页条件查询图书
     * @param pageNum
     * @return
     */
    PageInfo<Book> searchBooks(Integer pageNum,Book book);

    /**
     * 修改图书
     * @param book
     * @return
     */
    Integer editBookById(Book book);

    void addBook(Book book);

    /**
     * 分页条件查询当前借阅图书
     * @param pageNum
     * @return
     */
    PageInfo<Book> searchMyBorrow(Integer pageNum,Book book);

    Boolean returnBook(Integer id);

    /**
     * 确认归还图书
     * @param id
     */
    void returnConfirm(Integer id);

    PageInfo<Book> searchBorrow(Integer pageNum, Book book);
}
