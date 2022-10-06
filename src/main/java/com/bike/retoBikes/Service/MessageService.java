package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Message;
import com.bike.retoBikes.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public List<Message> getAll(){
        return messageRepo.getAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return messageRepo.getMessage(messageId);
    }

    public Message save(Message message){
        if(message.getIdMessage()==null){
            return messageRepo.save(message);
        }else{
            Optional<Message> e= messageRepo.getMessage(message.getIdMessage());
            if(e.isPresent()){
                return message;
            }else{
                return messageRepo.save(message);
            }
        }
    }
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= messageRepo.getMessage(message.getIdMessage());
            if(e.isPresent()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepo.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean deleteMessage (int id){
        boolean del = getMessage(id).map(message -> {
            messageRepo.delete(message);
            return true;
        }).orElse(false);
        return del;
    }
}
