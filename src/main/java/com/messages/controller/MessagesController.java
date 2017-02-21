package com.messages.controller;

import com.messages.data.Author;
import com.messages.data.Messages;
import com.messages.repository.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by WagnerMestrinho on 2/9/17.
 */
@Controller
public class MessagesController {

    @RequestMapping("/")
    public String loginRedirect(){
        return "/open/login";
    }

    @Autowired
    MessagesRepo messagesRepo;

    @RequestMapping(path = "/secure/write", method = RequestMethod.POST)
    public String createMessage(Model dataToJsp, HttpSession session,
                                @RequestParam String content){

        // same as one-liner below
        //
        //      Object userAsObject = session.getAttribute("user");
        //        Author anAuthor = (Author)userAsObject;
        //        String userName = anAuthor.getName();

        String author = ((Author)session.getAttribute("user")).getName();
        Messages tmp = new Messages(content, author);
        tmp.setTime(Calendar.getInstance().getTime());

        messagesRepo.save(tmp);
        //if saved add message
        if(tmp.getId()>0) {
            dataToJsp.addAttribute("msg_success",
                    String.format( "%s you're Great" , author));
        }
        return "forward:/secure/messages";
    }

    /* @RequestMapping(path = "/secure/messages")
    public String listMessages(Model xyz){
        String destination = "wall";
        Iterable found = messagesRepo.findAll();
        // put list into model
        xyz.addAttribute("mList", found);
        // go to jsp
        return destination;
    }*/
    @RequestMapping(path = "/secure/messages")
    public String listMessages(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             Model model){

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
        // put list into model
        model.addAttribute("mList", found);
        // go to jsp
        return destination;
    }
}
