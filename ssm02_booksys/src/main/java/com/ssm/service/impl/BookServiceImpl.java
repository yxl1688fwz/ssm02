package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mapper.BookMapper;
import com.ssm.mapper.RecordMapper;
import com.ssm.pojo.Book;
import com.ssm.pojo.Record;
import com.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RecordMapper recordMapper;
    /**
     * 分页查询新图书
     * @return
     */
    @Override
    public PageInfo<Book> selectNewBooks(Integer pageNum) {
        //开启分页 每页5条信息
        PageHelper.startPage(pageNum,5);
        //查询所有员工
        List<Book> list = bookMapper.getAllBook();
        //获取分页数据 底部导航栏 3
        PageInfo<Book> page=new PageInfo<>(list,3);
        return page;
    }

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Integer id) {
        Book book=bookMapper.getBookById(id);
        return book;
    }

    /**
     * 借阅图书
     * @param book
     * @return
     */
    @Override
    public Integer borrowBook(Book book) {
        Book b=this.getBookById(book.getBookId());
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        book.setBookBorrowtime(dateFormat.format(new Date()));
        //设置为借阅中
        book.setBookStatus("1");
        //
        book.setBookPrice(b.getBookPrice());

        return bookMapper.updateBook(book);
    }

    /**
     * 分页条件查询图书
     * @return
     */
    @Override
    public PageInfo<Book> searchBooks(Integer pageNum,Book book) {
        //开启分页 每页5条信息
        PageHelper.startPage(pageNum,5);
        //查询所有图书
        List<Book> list = bookMapper.searchBook(book);
        //获取分页数据 底部导航栏 3
        PageInfo<Book> page=new PageInfo<>(list,3);
        return page;
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    @Override
    public Integer editBookById(Book book) {
        return bookMapper.updateBook(book);
    }

    /**
     * 新增图书
     * @param book
     */
    @Override
    public void addBook(Book book) {
    bookMapper.addBook(book);
    }

    /**
     * 分页条件查询当前用户借阅图书
     * @param pageNum
     * @param book
     * @return
     */
    @Override
    public PageInfo<Book> searchMyBorrow(Integer pageNum, Book book) {
        //开启分页 每页5条信息
        PageHelper.startPage(pageNum,5);
        //查询所有当前借阅图书
        List<Book> list = bookMapper.searchMyBorrow(book);
        //获取分页数据 底部导航栏 3
        PageInfo<Book> page=new PageInfo<>(list,3);
        return page;
    }

    /**
     * 分页条件查询所有借阅图书
     * @param pageNum
     * @param book
     * @return
     */
    @Override
    public PageInfo<Book> searchBorrow(Integer pageNum, Book book) {
        //开启分页 每页5条信息
        PageHelper.startPage(pageNum,5);
        //查询所有当前借阅图书
        List<Book> list = bookMapper.searchBorrow(book);
        //获取分页数据 底部导航栏 3
        PageInfo<Book> page=new PageInfo<>(list,3);
        return page;
    }

    /**
     * 还书
     * @param id
     * @return
     */
    @Override
    public Boolean returnBook(Integer id) {
        Book book=bookMapper.getBookById(id);
        //设为归还中
        book.setBookStatus("2");
        bookMapper.updateBook(book);
        return null;
    }

    /**
     * 确认归还图书
     * @param id
     */
    @Override
    public void returnConfirm(Integer id) {
        Book book=bookMapper.getBookById(id);
        Record record=this.setRecord(book);

        book.setBookStatus("0");
        book.setBookBorrower("");
        book.setBookBorrowtime("");
        book.setBookReturntime("");

        int rs = bookMapper.updateBook(book);
        //归还成功，新增借阅记录
        if(rs==1){
            recordMapper.addRecord(record);
        }
    }

    /**
     * 设置借阅记录
     * @param book
     * @return
     */
    private Record setRecord(Book book){
        Record record=new Record();

        record.setRecordBookname(book.bookName);
        record.setRecordBookisbn(book.bookIsbn);
        record.setRecordBorrower(book.bookBorrower);
        record.setRecordBorrowtime(book.bookBorrowtime);

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        record.setRecordRemandtime(dateFormat.format(new Date()));

        return record;
    }
}
