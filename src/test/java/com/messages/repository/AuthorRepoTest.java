package com.messages.repository;

import com.messages.data.Messages;
import com.messages.data.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by WagnerMestrinho on 2/9/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private MessagesRepo messagesRepo;

    @Test
    public void test() {
        //create message
        Messages msg1 = new Messages();
        msg1.setContent("hello");
        msg1.setAuthor("Bubba");
        messagesRepo.save(msg1);

        //create user
        Author user1 = new Author();
        user1.setName("Bubba");
        user1.setPassword("pass");
        authorRepo.save(user1);
    }
}