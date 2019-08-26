package net.nigne.yourtour.common.domain;

import net.nigne.yourtour.exception.NotFoundException;
import org.junit.Test;

public class AddressTest {

    @Test(expected = NotFoundException.class)
    public void 주소없음() {
        Address address = new Address("");
    }

    @Test
    public void 주소있음() {
        Address address = new Address("서울시 강남구 논현동");
    }
}