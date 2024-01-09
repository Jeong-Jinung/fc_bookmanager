package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.Member;
import com.fastcampus.jpa.bookmanager.domain.MemberHistory;
import com.fastcampus.jpa.bookmanager.repository.MemberHistoryRepository;
import com.fastcampus.jpa.bookmanager.support.BeanUtils;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


/**
 * listener를 사용하면, 이벤트가 발생할 때마다 해당 메소드가 실행된다.
 * 자동화 시킬 수 있다는 걸 명심하자!
 */
public class MemberEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        Member member = (Member) o;

        MemberHistory memberHistory = new MemberHistory();
//        memberHistory.setMemberId(member.getId());
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());
        memberHistory.setMember(member);

        memberHistoryRepository.save(memberHistory);
    }

}
