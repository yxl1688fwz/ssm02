package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Book;
import com.ssm.pojo.User;
import com.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    //分页查询图书信息
    @RequestMapping(value = "/selectNewbooks/{pageNum}",method = RequestMethod.GET)
    public String getBooksPage(@PathVariable("pageNum") Integer pageNum, Model model) {
        //获取图书分页信息
        PageInfo<Book> page=bookService.selectNewBooks(pageNum);
        //将分页信息共享请求域
        model.addAttribute("page",page);
        //跳转
        return "book_new";
    }

    @RequestMapping("/to/selectNewbooks")
    public String to(){
        return "redirect:/book/selectNewbooks/1";
    }


    //根据id查询图书
    @RequestMapping(value = "/getBookById/{id}",method = RequestMethod.GET)
    public String getBookById(@PathVariable("id") Integer id,Model model){
        //获取图书信息
        Book book=bookService.getBookById(id);
        //数据共享请求域
        model.addAttribute("book",book);
        return "main";
    }

    //借阅图书
    @RequestMapping("/borrowBook/{id}")
    public String borrowBook(@PathVariable("id")Integer id,Book book,HttpSession session,Model model){
        //获取登录用户名
        String userName= ((User)session.getAttribute("loginUser")).userName;
        //存入借阅人
        book.setBookBorrower(userName);
        book.setBookId(id);
        try{
            Integer count=bookService.borrowBook(book);
            if(count==1){
                model.addAttribute("msg","借阅成功！请到图书馆领取!");
            } else {
                model.addAttribute("msg","借阅图书失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/book/selectNewbooks/1";
    }

    //跳转借阅
    @RequestMapping("/to/book_modal/{id}")
    public String toBookModal(@PathVariable("id") Integer id,Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book_modal";
    }

    //分页条件查询
    @RequestMapping("/search/{pageNum}")
    public String searchBook(@PathVariable("pageNum") Integer pageNum,Book book, Model model){
        //获取图书分页信息
        PageInfo<Book> page=bookService.searchBooks(pageNum,book);
        //将分页信息共享请求域
        model.addAttribute("page",page);
        //跳转
        return "books";
    }

    //跳转修改
    @RequestMapping("/to/book_edit/{id}")
    public String toBookEdit(@PathVariable("id") Integer id,Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book_edit";
    }

    //修改图书
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id")Integer id, Book book){
        book.setBookId(id);
        int rs = bookService.editBookById(book);
        return "redirect:/book/search/1";
    }

    //跳转新增
    @RequestMapping("/to/book_add")
    public String toBookAdd(){
        return "book_add";
    }

    //新增
    @RequestMapping("/add")
    public String addBook(Book book){
        bookService.addBook(book);
        return "redirect:/book/search/1";
    }

    //跳转当前借阅
    @RequestMapping("/searchBorrow/{pageNum}")
    public String tosearchBorrow(@PathVariable("pageNum") Integer pageNum,Book book, Model model,HttpServletRequest request){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //管理员查询所有
        if(loginUser.userRole.equals("ADMIN")){
            //获取图书分页信息
            PageInfo<Book> page=bookService.searchBorrow(pageNum,book);
            //将分页信息共享请求域
            model.addAttribute("page",page);
        }
        else {
            book.setBookBorrower(loginUser.userName);
            //获取图书分页信息
            PageInfo<Book> page=bookService.searchMyBorrow(pageNum,book);
            //将分页信息共享请求域
            model.addAttribute("page",page);
        }
        return "book_borrow";

    }

    //归还图书
    @RequestMapping("/returnBook/{id}")
    public String returnBook(@PathVariable("id")Integer id,HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        Boolean rb = bookService.returnBook(id);
        return "redirect:/book/searchBorrow/1";
    }

    //确认归还图书
    @RequestMapping("/returnConfirm/{id}")
    public String returnConfirm(@PathVariable("id")Integer id,HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser.userRole.equals("ADMIN")){
            bookService.returnConfirm(id);
        }
        return "redirect:/book/searchBorrow/1";
    }

}
