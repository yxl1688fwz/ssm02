package com.ssm.mapper;

import com.ssm.pojo.Record;

import java.util.List;

public interface RecordMapper {
    Integer addRecord(Record record);

    List<Record> searchRecords(Record record);
}
