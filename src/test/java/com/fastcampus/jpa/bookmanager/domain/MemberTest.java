package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

class MemberTest extends Object {

    @Test
    void test() {
        Member member = new Member();
        member.setEmail("martin@fastcampus.com");
        member.setName("martin");
        System.out.println(">>> " + member.toString());
    }

}