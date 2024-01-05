package com.fastcampus.jpa.bookmanager.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.Member;
import com.fastcampus.jpa.bookmanager.domain.Publisher;
import com.fastcampus.jpa.bookmanager.domain.Review;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        Member member = memberRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Review : " + member.getReviews());
        System.out.println("Book : " + member.getReviews().get(0).getBook());
        System.out.println("Publisher : " + member.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenMember(), givenBook(givenPublisher()));
    }

    private Member givenMember() {
        return memberRepository.findByEmail("martin@fastcampus.com");
    }

    private void givenReview(Member member, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재밌어요.");
        review.setScore(5.0f);
        review.setMember(member);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }
}