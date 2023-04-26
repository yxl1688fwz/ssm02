package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.mapper.RecordMapper;
import com.ssm.pojo.Book;
import com.ssm.pojo.Record;
import com.ssm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 新增借阅记录
     * @param record
     * @return
     */
    @Override
    public Integer addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    /**
     * 分页查询借阅记录
     * @param pageNum
     * @param record
     * @return
     */
    @Override
    public PageInfo<Record> searchRecords(Integer pageNum, Record record) {
        //开启分页 每页5条信息
        PageHelper.startPage(pageNum,5);
        //查询所有当前借阅图书
        List<Record> list = recordMapper.searchRecords(record);
        //获取分页数据 底部导航栏 3
        return new PageInfo<>(list,3);
    }
}
