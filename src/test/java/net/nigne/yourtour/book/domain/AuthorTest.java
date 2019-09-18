package net.nigne.yourtour.book.domain;

import net.nigne.yourtour.exception.NotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_저자_생성() {
        Author author = new Author("");
    }

    @Test
    public void 정상적인_저자_생성() {
        Author author = new Author("한소진");
    }

}