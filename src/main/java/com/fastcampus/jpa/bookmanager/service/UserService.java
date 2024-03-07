package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Member;
import com.fastcampus.jpa.bookmanager.repository.MemberRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void put() {
        Member member = new Member();
        member.setName("martin");
        member.setEmail("newMember@fastcampus.com");

        entityManager.persist(member); // -> 영속성 컨텍스트
//        entityManager.detach(member); // -> 영속성 컨텍스트에서 분리


        //영속성 컨텍스트가 관리하는 더티체크
        member.setName("newUserAfterPersist"); // persist 이후에도 변경된 값이 반영된다.
        entityManager.merge(member); // -> 영속성 컨텍스트에 다시 등록
//        entityManager.flush(); // -> 영속성 컨텍스트의 변경사항을 데이터베이스에 반영
//        entityManager.clear(); // -> 영속성 컨텍스트 초기화

//        entityManager.remove(member); // -> 영속성 컨텍스트에서 삭제

        Member member1 = memberRepository.findById(1L).get();
        entityManager.remove(member1);

        member1.setName("marrrrrrrtin");
        entityManager.merge(member1);
    }



}
