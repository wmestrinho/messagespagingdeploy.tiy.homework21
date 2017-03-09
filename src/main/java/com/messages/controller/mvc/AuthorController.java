package com.messages.controller.mvc;

import com.messages.data.Author;
import com.messages.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by WagnerMestrinho on 2/9/17.
 */
@Controller
public class AuthorController {

    @Autowired
    AuthorRepo authorRepo;

    @RequestMapping(path = "/open/authenticate", method = RequestMethod.POST)
    public String login(HttpSession session, Model data, @RequestParam(name = "author") String name, @RequestParam String password) {
        Author found = authorRepo.findByNameAndPassword(name, password);
        String destinationView = "write";

        if (found == null) {
            // no user found, login must fail

            destinationView = "/open/login";
            data.addAttribute("err_login_msg", "User/Pass combination not found.");
        } else {

            session.setAttribute("user", found);
            destinationView = "redirect:/secure/write.jsp";
        }

        return destinationView;
    }
}
