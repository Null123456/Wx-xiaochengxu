package com.ssk.service;

import com.ssk.mapper.MessageMapper;
import com.ssk.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    public void insert(Message mes){
        messageMapper.insert(mes);
    }
    public List<Message> selectAll(){
        List<Message> messages = messageMapper.selectAll();
        return messages;
    }

    public Message selectByid(String id){
        Message message = messageMapper.selectByid(id);
        return message;
    }

    public List<Message> selectBymessage(String message){
        List<Message> message1 = messageMapper.selectBymessage(message);
        return message1;
    }
}
