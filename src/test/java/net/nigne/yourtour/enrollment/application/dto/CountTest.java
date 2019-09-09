package net.nigne.yourtour.enrollment.application.dto;

import net.nigne.yourtour.enrollment.domain.Count;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_권수없음() {
        //given
        Count count = new Count(null);
    }

    @Test(expected = WrongValueException.class)
    public void 비정상적인_권수_minus3() {
        //given
        Count count = new Count(-3L);
    }

    @Test
    public void 정상적인_권수_3() {
        //given
        Count count = new Count(3L);
    }

}