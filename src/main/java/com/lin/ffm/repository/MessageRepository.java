package com.lin.ffm.repository;

import com.lin.ffm.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
