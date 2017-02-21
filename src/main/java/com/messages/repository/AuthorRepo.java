package com.messages.repository;

import com.messages.data.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by WagnerMestrinho on 2/9/17.
 */
public interface AuthorRepo extends CrudRepository<Author, Long> {
    public Author findByNameAndPassword(String name, String password);
}
