package com.ssk.Controller;

import com.ssk.pojo.Global;
import com.ssk.pojo.Message;
import com.ssk.service.MessageService;
import com.ssk.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.UUID;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "save")
    @ResponseBody
    public String save(Message mes){
        mes.setId(UUID.randomUUID().toString());
        mes.setOpenid(Global.getOpenId());
        messageService.insert(mes);
        return "200";
    }

    @RequestMapping(value = "select")
    @ResponseBody
    public List<Message> selectAll(){
        List<Message> messagesList = messageService.selectAll();
        return messagesList;
    }

    @RequestMapping(value = "find")
    @ResponseBody
    public ResultUtil selectAlll(){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode("200");
        List<Message> messagesList = messageService.selectAll();
        resultUtil.setData(messagesList);
        return resultUtil;
    }


    @RequestMapping(value = "selectByid")
    @ResponseBody
    public Message selectByid(String id){
        Message message = messageService.selectByid(id);
        return message;
    }

    @RequestMapping(value = "selectBymessage")
    @ResponseBody
    public List<Message> selectBymessage(String message){
        List<Message> message1 = messageService.selectBymessage(message);
        return message1;
    }


}
