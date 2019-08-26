package net.nigne.yourtour.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");

    private String email;

    public Email(String email) {
        this.email = validation(email);
    }

    private String validation(String email) {
        if(StringUtils.isEmpty(email)) {
            throw new NotFoundException("이메일이 없습니다.");
        }
        if(!EMAIL_PATTERN.matcher(email).matches()) {
            throw new WrongValueException("이메일 형식이 잘못되었습니다.");
        }

        return email;
    }
}
