package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    @Test // 필요 없는 테스트이다. -> 학습을 위한 테스트
    void crudTest2() {
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository
            .findById(1L)
            .orElseThrow(RuntimeException::new)
            .getBook();

        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository
            .findById(7L)
            .orElseThrow(RuntimeException::new)
            .getBookReviewInfo();

        System.out.println(">>> " + result2);

    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        return bookRepository.save(book);

    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

}