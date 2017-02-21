package com.messages.repository;

import com.messages.data.Messages;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by WagnerMestrinho on 2/9/17.
 */
public interface MessagesRepo extends PagingAndSortingRepository<Messages, Long>{
}
