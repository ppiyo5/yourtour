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
public class PhoneNumber {

    private final static Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$");

    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = validation(phoneNumber);
    }

    private String validation(String phoneNumber) {
        if(StringUtils.isEmpty(phoneNumber)) {
            throw new NotFoundException("전화번호가 없습니다.");
        }

        if(!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new WrongValueException(String.format("전화번호가 잘못되었습니다."));
        }

        return phoneNumber;
    }
}
