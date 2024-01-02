package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.Member;
import com.fastcampus.jpa.bookmanager.domain.MemberHistory;
import com.fastcampus.jpa.bookmanager.repository.MemberHistoryRepository;
import com.fastcampus.jpa.bookmanager.support.BeanUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class MemberEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o) {
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        if (o instanceof Member) {
            Member member = (Member) o;
            MemberHistory memberHistory = new MemberHistory();
            memberHistory.setEmail(member.getEmail());
            memberHistory.setName(member.getName());

            memberHistoryRepository.save(memberHistory);
        }
//        Member member = (Member) o;
//
//        MemberHistory memberHistory = new MemberHistory();
//        memberHistory.setMemberId(member.getId());
//        memberHistory.setName(member.getName());
//        memberHistory.setEmail(member.getEmail());
//
//        memberHistoryRepository.save(memberHistory);
    }

}
