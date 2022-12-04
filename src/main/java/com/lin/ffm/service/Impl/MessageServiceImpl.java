package com.lin.ffm.service.Impl;

import com.lin.ffm.pojo.Message;
import com.lin.ffm.repository.MessageRepository;
import com.lin.ffm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message findMsgById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        Message m = message.get();
        return m;
    }

    @Override
    public Message editMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
}
