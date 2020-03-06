package com.ssk.mapper;


import com.ssk.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
     void insert(Message mes);
     List<Message> selectAll();
     Message selectByid(String id);
     List<Message> selectBymessage(@Param(value="message")String message);
}
