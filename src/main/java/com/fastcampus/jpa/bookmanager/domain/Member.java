package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.MemberEntityListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//@ToString // toString() 메소드 자동 생성
//@Getter // getter() 메소드 자동 생성
//@Setter // setter() 메소드 자동 생성
//@EqualsAndHashCode // equals()와 hashCode() 메소드 자동 생성
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@RequiredArgsConstructor // final 필드를 인자로 받는 생성자 @NonNull이 붙은 필드만 인자로 받음
@Data // @ToString, @Getter, @Setter, @EqualsAndHashCode, @RequiredArgsConstructor를 한꺼번에 설정
@Builder // 빌더 패턴을 사용할 수 있게 해줌
@Entity
@EntityListeners(value = MemberEntityListener.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 생성되는 값
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING) // EnumType.ORDINAL : enum 순서대로 0, 1, 2, 3, ... 으로 저장됨
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", insertable = false, updatable = false) // member entity에서 history entity 수정하지 못하게 방지
    @ToString.Exclude
    private List<MemberHistory> memberHistories = new ArrayList(); // NPE 방지

    @OneToMany
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
