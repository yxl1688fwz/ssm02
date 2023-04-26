package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Book;
import com.ssm.pojo.Record;
import com.ssm.pojo.User;
import com.ssm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/searchRecords/{pageNum}")
    public String searchRecord(@PathVariable("pageNum")Integer pageNum, Record record, HttpServletRequest request, Model model){
        //获取登录用户信息
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //如果不是管理员 设置借阅人为当前用户
        if(!loginUser.userRole.equals("ADMIN")){
            record.setRecordBorrower(loginUser.userName);
        }
        //获取图书分页信息
        PageInfo<Record> page=recordService.searchRecords(pageNum,record);
        //将分页信息共享请求域
        model.addAttribute("page",page);

        return "record";
    }
}
