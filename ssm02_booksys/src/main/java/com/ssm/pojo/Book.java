package com.ssm.pojo;


import java.util.Date;

public class Book {

    //图书编号
    public Integer bookId;
    //图书名称
    public String bookName;
    //图书标准ISBN
    public String bookIsbn;
    //图书出版社
    public String bookPress;
    // 图书作者
    public String bookAuthor;
    //图书页数
    public Integer bookPagination;
    //图书价格
    public Double bookPrice;
    //图书上架时间
    public Date bookUploadtime;
    //图书状态
    public String bookStatus;
    //图书借阅人
    public String bookBorrower;
    //图书借阅时间
    public String bookBorrowtime;
    //图书预计归还时间
    public String bookReturntime;

    public Book() {
    }

    public Book(Integer bookId, String bookName, String bookIsbn, String bookPress, String bookAuthor, Integer bookPagination, Double bookPrice, Date bookUploadtime, String bookStatus, String bookBorrower, String bookBorrowtime, String bookReturntime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.bookPress = bookPress;
        this.bookAuthor = bookAuthor;
        this.bookPagination = bookPagination;
        this.bookPrice = bookPrice;
        this.bookUploadtime = bookUploadtime;
        this.bookStatus = bookStatus;
        this.bookBorrower = bookBorrower;
        this.bookBorrowtime = bookBorrowtime;
        this.bookReturntime = bookReturntime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookPagination() {
        return bookPagination;
    }

    public void setBookPagination(Integer bookPagination) {
        this.bookPagination = bookPagination;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Date getBookUploadtime() {
        return bookUploadtime;
    }

    public void setBookUploadtime(Date bookUploadtime) {
        this.bookUploadtime = bookUploadtime;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookBorrower() {
        return bookBorrower;
    }

    public void setBookBorrower(String bookBorrower) {
        this.bookBorrower = bookBorrower;
    }

    public String getBookBorrowtime() {
        return bookBorrowtime;
    }

    public void setBookBorrowtime(String bookBorrowtime) {
        this.bookBorrowtime = bookBorrowtime;
    }

    public String getBookReturntime() {
        return bookReturntime;
    }

    public void setBookReturntime(String bookReturntime) {
        this.bookReturntime = bookReturntime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPagination=" + bookPagination +
                ", bookPrice=" + bookPrice +
                ", bookUploadtime=" + bookUploadtime +
                ", bookStatus='" + bookStatus + '\'' +
                ", bookBorrower='" + bookBorrower + '\'' +
                ", bookBorrowtime=" + bookBorrowtime +
                ", bookReturntime=" + bookReturntime +
                '}';
    }
}
