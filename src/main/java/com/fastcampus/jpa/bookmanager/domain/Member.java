package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.MemberEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Enumerated(value = EnumType.STRING)
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
