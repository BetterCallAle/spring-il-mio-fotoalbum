package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepo;

    //Get all messages
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    //Create a new Message
    public Message createNewMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setName(message.getName());
        newMessage.setSurname(message.getSurname());
        newMessage.setEmail(message.getEmail());
        newMessage.setMessage(message.getMessage());
        return messageRepo.save(newMessage);
    }
}
