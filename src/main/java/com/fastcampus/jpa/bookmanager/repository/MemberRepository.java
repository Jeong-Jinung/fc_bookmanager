package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Member;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Member findByEmail(String email);

    Member getByEmail(String email);

    Member readByEmail(String email);

    Member queryByEmail(String email);

    Member searchByEmail(String email);

    Member streamByEmail(String email);

    Member findMemberByEmail(String email);

    Member findSomethingByEmail(String email);

    @Query(value = "select * from member limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();

}
