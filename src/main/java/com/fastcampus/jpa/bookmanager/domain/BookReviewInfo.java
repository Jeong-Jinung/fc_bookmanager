package com.fastcampus.jpa.bookmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long bookId;
    @OneToOne(optional = false) // optional = false : 반드시 연관관계가 설정되어야 한다.
    private Book book;

    private float averageReviewScore;

    /**
     * primitive key type은 null이 들어갈 수 없다.
     */
    private int reviewCount;



}
