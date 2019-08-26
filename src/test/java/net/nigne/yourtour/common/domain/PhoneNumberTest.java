package net.nigne.yourtour.common.domain;

import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.junit.Test;

public class PhoneNumberTest {

    @Test(expected = NotFoundException.class)
    public void 비정상적인_폰번호없음() {
        PhoneNumber phoneNumber = new PhoneNumber("");
    }

    @Test(expected = WrongValueException.class)
    public void 비정상적인_폰번호_ㄱㄴㄷㄹ() {
        PhoneNumber phoneNumber = new PhoneNumber("ㄱㄴㄷㄹ");
    }

    @Test(expected = WrongValueException.class)
    public void 비정상적인_폰번호_바없음() {
        PhoneNumber phoneNumber = new PhoneNumber("01012345678");
    }

    @Test
    public void 정상적인_폰번호() {
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");
    }


}