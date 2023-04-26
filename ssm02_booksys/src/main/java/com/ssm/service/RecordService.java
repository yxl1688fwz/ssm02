package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Record;

import java.util.List;

public interface RecordService {
    Integer addRecord(Record record);

    PageInfo<Record> searchRecords(Integer pageNum, Record record);
}
