package com.fastcampus.jpa.bookmanager.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jpa.bookmanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test() {
        userService.put();
        System.out.println(">>> " + memberRepository.findByEmail("newMember@fastcampus.com"));
        memberRepository.findAll().forEach(System.out::println);
    }

}