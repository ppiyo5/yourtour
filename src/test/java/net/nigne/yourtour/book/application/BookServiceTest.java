package net.nigne.yourtour.book.application;

import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void Id로_책_조회() {
        //given
        given(bookRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                Book.createBuilder()
                                        .name("이펙티브 자바 (Effective Java)")
                                        .author("조슈아 블로크")
                                        .category("IT")
                                        .publisher("인사이트")
                                        .price(10000L)
                                        .build()
                        )
                )
        ;

        Book book = Book.createBuilder()
                .name("이펙티브 자바 (Effective Java)")
                .author("조슈아 블로크")
                .category("IT")
                .publisher("인사이트")
                .price(10000L)
                .build();

        //when
        Book book1 = bookService.findById(1L);

        //then
        assertThat(book1.getName()).isEqualTo(book.getName());
        assertThat(book1.getAuther()).isEqualTo(book.getAuther());
    }


}