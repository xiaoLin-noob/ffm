package com.lin.ffm.service;

import com.lin.ffm.pojo.Message;


public interface MessageService {

    Message findMsgById(int id);

    Message editMessage(Message message);

    Message addMessage(Message message);

    void deleteMessage(int id);

}
