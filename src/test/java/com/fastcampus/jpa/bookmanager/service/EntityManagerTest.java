package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Member;
import com.fastcampus.jpa.bookmanager.repository.MemberRepository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select m from Member m").getResultList());
    }

    @Test
    void cacheFindTest() {
//        System.out.println(memberRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(memberRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(memberRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(memberRepository.findById(2L).get());
//        System.out.println(memberRepository.findById(2L).get());
//        System.out.println(memberRepository.findById(2L).get());
        /**
         * @Transactional을 붙이면 지연쓰기가 발생한다. -> 실제 DB에 적용되어야 하는 쿼리만 실행한다.
         * 지연쓰기로 인해 @Transactional을 붙이면 delete 쿼리가 실행되지 않는다.
         * 바로 롤백되서 실제 commit이 일어나지 않기 때문이다.
         */
        memberRepository.deleteById(1L);
    }

    /**
     * Save 메소드에는 @Transacional이 붙어있다.
     * @Transactional은 기본적으로 readOnly = false이다.
     * 따라서 save 메소드를 호출하면 트랜잭션이 시작된다.
     * Propagation.REQUIRED 이기 때문에 상위 Trasaction이 없으면 각각 독립적이다.
     * 하나의 Transaction으로 묶이게 되면 업데이트는 한 번 일어나게 된다.
     */
    @Test
    void cacheFindTest2() {
        Member member = memberRepository.findById(1L).get();
        member.setName("marrrrrrtin");
        memberRepository.save(member);

        System.out.println("------------------------");

        member.setEmail("marrrrrrtin@fastcampus.com");
        memberRepository.save(member);

        /**
         * 영속성 컨텍스트에 쌓여있는 데이터는 EntityManger가 관리한다.
         * 개발자가 원하는 시간에 DB에 영속화 시키려면 flush를 해야한다.
         * flush 시점에 db에 모두 반영한다. 과도한 남발은 좋지 1차 캐시의 장점을 해칠 수 있다.
         * 영속성컨텍스트에서 각각 가지고 있다가 Merge 해 한 번에 반영한다.
         * 실제 영속성 컨텍스트와 DB가 동기화 되는 시점은 Flush를 호출하는 시점이다. -> 개발자가 의도적으로 DB에 반영하고 싶을 때
         */
//        memberRepository.flush();

        /**
         * 먼저 Update 된것 처럼 보인다. 영속성 컨텍스트와 DB의 차이가 발생하는 순간이다.
         * flush를 하면 실제 update 쿼리가 나가게 된다.
         * 그 후 다시 셀렉하면 1차캐시에서 조회하게 된다.
         */
//        System.out.println(">>> 1 : " + memberRepository.findById(1L).get());
//        memberRepository.flush();
//        System.out.println(">>> 2 : " + memberRepository.findById(1L).get()); // auto flush가 일어남 @transactional이 없으면 Commit이 없어서 update 쿼리 날라가지 않음
        /**
         * 1번 아이디의 경우 영속성 컨텍스트와 DB를 비교해서 최신 값인 영속성 컨텍스트 값을 사용하고, 나머지는 DB 값을 사용 해야 할것이다.
         * 데이터를 비교해서 Merge해야 되는 과정이 추가가 된다. 우리가 jpa 개발자라면 어떻게 코딩해야 할까???
         * 이럴 경우 영속성 컨텍스트에 있는 값을 모두 DB에 반영하고, 다시 select를 해오는 방식으로 동작한다.
         */
        System.out.println(memberRepository.findAll()); //
    }

}
