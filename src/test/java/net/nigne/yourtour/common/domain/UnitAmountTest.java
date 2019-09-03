package net.nigne.yourtour.common.domain;

import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitAmountTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_금액없음() {
        //given
        UnitAmount unitAmount = new UnitAmount(null);
    }

    @Test(expected = WrongValueException.class)
    public void 비정상적인_금액_minus_10000() {
        //given
        UnitAmount unitAmount = new UnitAmount(-10000L);
    }

    @Test
    public void 정상적인_금액_10000() {
        //given
        UnitAmount unitAmount = new UnitAmount(10000L);
    }
}