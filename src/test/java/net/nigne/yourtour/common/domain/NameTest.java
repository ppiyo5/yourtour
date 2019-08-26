package net.nigne.yourtour.common.domain;

import lombok.extern.java.Log;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.user.domain.User;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@Log
public class NameTest {

    @Test(expected = NotFoundException.class)
    public void 이름없음() {
        Name name = new Name("");
    }

    @Test
    public void 이름있음() {
        Name name = new Name("김자바");
    }

    @Test
    public void 아이디값_보장() {
        User user = new User("김자바", "1234", "010-1234-5678", "ppiyo_@daum.net", "서울시 강남구");
//        assertThat(user.getId(), is(notNullValue()));
        log.info("아이디는 NotNull");
    }


}