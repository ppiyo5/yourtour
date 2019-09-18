package net.nigne.yourtour.book.domain;

import net.nigne.yourtour.exception.NotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PublisherTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_출판사_생성() {
        //given
        Publisher publisher = new Publisher("");
    }

    @Test
    public void 정상적인_출판사_생성() {
        //given
        Publisher publisher = new Publisher("한빛미디어");
    }

}