package net.nigne.yourtour.book.domain;

import net.nigne.yourtour.exception.NotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_카테고리_생성() {
        //given
        Category category = new Category("");
    }

    @Test
    public void 정상적인_카테고리_생성() {
        //given
        Category category = new Category("IT");
    }

}