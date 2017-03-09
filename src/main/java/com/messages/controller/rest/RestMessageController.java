package com.messages.controller.rest;

import com.messages.data.Messages;
import com.messages.repository.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WagnerMestrinho on 2/25/17.
 */
@RestController
public class RestMessageController {

    @Autowired
    MessagesRepo messagesRepo;
    @RequestMapping(path = "/rest/messages/", method = RequestMethod.GET)
    public Page<Messages> listMessages(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", required = false) Integer size)
                                       {
        String destination = "/secure/wall";
        if(page == null){
            page = 0;
        }
        if(size == null){
            size = 2;
        }
        Sort s = new Sort(Sort.Direction.DESC, "time");
        PageRequest pr = new PageRequest(page, size, s);
        Page<Messages> found = messagesRepo.findAll(pr);
        // go to jsp
        return found;
    }

    @RequestMapping(path = "/rest/messages/getOne/{id}",  method = RequestMethod.GET)
    public Messages findByMessageId(@PathVariable Long id) throws Exception {
        // save to database
        Messages found = messagesRepo.findOne(id);
        return found;
    }

    @RequestMapping(path = "/rest/messages/update",  method = RequestMethod.PATCH)
    public Messages editMessage(@RequestBody Messages saveToDB) throws Exception {
        // save to database
        if(saveToDB.getId() == 0){
            throw new Exception("Patch update must specify message id.");
        }

        Messages tmp = messagesRepo.findOne(saveToDB.getId());
        if(tmp != null) {
            messagesRepo.save(saveToDB);
        }else{
            throw new Exception("Id specified does not exist! id:"+saveToDB.getId());

        }

        return saveToDB;
    }

        @RequestMapping(path = "/rest/write", method = RequestMethod.POST)
        public Messages createMessage(@RequestBody Messages saveToDB) throws Exception {
            // save to database
            messagesRepo.save(saveToDB);
            return saveToDB;
        }
    }

