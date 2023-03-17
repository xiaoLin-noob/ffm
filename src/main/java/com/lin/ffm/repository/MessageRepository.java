package com.lin.ffm.repository;

import com.lin.ffm.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MessageRepository extends JpaRepository<Message,Integer> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Message m set m.img = ?2 where m.id = ?1")
    int changeImg(int id, byte[] img);


}
