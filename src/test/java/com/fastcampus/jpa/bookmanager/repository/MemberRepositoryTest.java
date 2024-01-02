package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.Member;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

    @Test
    void crud() { // create, read, update, delete
//        List<Member> members = memberRepository.findAll(Sort.by(Direction.DESC, "name"));
//        List<Member> members = memberRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
//        members.forEach(System.out::println);

//        Member member1 = new Member("jack", "jack@fasecampus.com");
//        Member member2 = new Member("seteve", "steve@fasecampus.com");

//        memberRepository.saveAll(Lists.newArrayList(member1, member2));

//        memberRepository.save(member1);

//        List<Member> members = memberRepository.findAll();
//        members.forEach(System.out::println);

//        Member member = memberRepository.getOne(1L); // 기본적으로 lazy fetch로 설정되어 있음
//        Member member = memberRepository.findById(1L).orElse(null);
//        System.out.println(member);
//        memberRepository.saveAndFlush(new Member("new martin", "newmartin@fastcampus.com"));
//        memberRepository.flush(); // db 반영 시점을 조절할 수 있음
//        memberRepository.findAll().forEach(System.out::println);

//        long count = memberRepository.count();
//        boolean exists = memberRepository.existsById(1L); // -> select count(*) from member where id = ?
//        memberRepository.delete(memberRepository.findById(1L).orElseThrow(RuntimeException::new));
//        memberRepository.deleteById(1L);
//        memberRepository.deleteAll();
//        memberRepository.deleteAllInBatch(memberRepository.findAllById(Lists.newArrayList(1L, 3L)));
//        memberRepository.deleteAllInBatch();
//        System.out.println("count = " + exists);
//        memberRepository.findAll().forEach(System.out::println);

//        Page<Member> members = memberRepository.findAll(PageRequest.of(0, 3));
//
//        System.out.println("page : " + members);
//        System.out.println("totalElements : " + members.getTotalElements());
//        System.out.println("totalPages : " + members.getTotalPages());
//        System.out.println("numberOfElements : " + members.getNumberOfElements()); // 현재 가져온 레코드 수
//        System.out.println("sort : " + members.getSort());
//        System.out.println("size : " + members.getSize());
//
//        members.getContent().forEach(System.out::println);

//        ExampleMatcher matcher = ExampleMatcher.matching()
//            .withIgnorePaths("name")
//            .withMatcher("email", endsWith());
//
//        Example<Member> example = Example.of(new Member("ma", "fastcampus.com"), matcher);

//        Member member = new Member();
//        member.setEmail("slow");
//        ExampleMatcher matcher = ExampleMatcher.matching()
//            .withMatcher("email", contains());
//        Example<Member> example = Example.of(member, matcher); // 실제로는 querydsl을 많이 사용한
//        memberRepository.findAll(example).forEach(System.out::println);

        memberRepository.save(new Member("david", "david@fastcampus.com"));

        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member.setEmail("martin-update@fastcampus.com");

        memberRepository.save(member);

    }

    @Test
    void select() {
//        System.out.println(memberRepository.findByName("dennis"));

//        System.out.println("findByEmail : " + memberRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println("getByEmail : " + memberRepository.getByEmail("martin@fastcampus.com"));
//        System.out.println("readByEmail : " + memberRepository.readByEmail("martin@fastcampus.com"));
//        System.out.println("queryByEmail : " + memberRepository.queryByEmail("martin@fastcampus.com"));
//        System.out.println("searchByEmail : " + memberRepository.searchByEmail("martin@fastcampus.com"));
//        System.out.println("streamByEmail : " + memberRepository.streamByEmail("martin@fastcampus.com"));
//        System.out.println("findMemberByEmail : " + memberRepository.findMemberByEmail("martin@fastcampus.com"));
//        System.out.println("findSomethingByEmail : " + memberRepository.findSomethingByEmail("martin@fastcampus.com"));
//        System.out.println("findByByName : " + memberRepository.findByByName("martin@fastcampus.com"));

//        System.out.println("findFirst1ByName : " + memberRepository.findFirst1ByName("martin"));
//        System.out.println("findTop1ByName : " + memberRepository.findTop1ByName("martin"));
    }

    @Test
    void insertAndUpdateTest() {
        Member member = new Member();

        member.setName("martin");
        member.setEmail("martin2@fastcampus.com");

        memberRepository.save(member);

        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member2.setName("marrrrrrtin");
        memberRepository.save(member2);
    }

    @Test
    void enumTest() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member.setGender(Gender.MALE);

        memberRepository.save(member);

        memberRepository.findAll().forEach(System.out::println);

        System.out.println(memberRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        Member member = new Member();
        member.setName("martin");
        member.setEmail("martin2@fastcampus.com");

        memberRepository.save(member);
        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member2.setName("marrrrrrtin");
        memberRepository.save(member2);

        memberRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        Member member = new Member();
        member.setEmail("martin2@fastcampus.com");
        member.setName("martin");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUpdatedAt(LocalDateTime.now());

        memberRepository.save(member);

        System.out.println(memberRepository.findByEmail("martin2@fastcampus.com"));
    }

    @Test
    void preUpdate() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + member);

        member.setName("martin22");
        memberRepository.save(member);

        System.out.println("to-be : " + memberRepository.findAll().get(0));
    }

    @Test
    void memberHistoryTest() {
        Member member = new Member();
        member.setName("martin-new");
        member.setEmail("martin-new@fastcampus.com");

        memberRepository.save(member);
        member.setName("martin-new-new");

        memberRepository.save(member);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }





}