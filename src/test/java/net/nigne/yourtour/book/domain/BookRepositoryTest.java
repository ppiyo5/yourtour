package net.nigne.yourtour.book.domain;

import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    @Test
    public void Book_조회() {
        //given
        createBook();

        //when
        List<Book> bookList = bookRepository.findAll();

        //then
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList.get(0).getName()).isEqualTo("이펙티브 자바 (Effective Java)");
        log.info("OK");
    }

    private void createBook() {
        bookRepository.save(Book.createBuilder()
                .name("이펙티브 자바 (Effective Java)")
                .author("조슈아 블로크")
                .category("IT")
                .publisher("인사이트")
                .price(10000L)
                .build());
    }



}