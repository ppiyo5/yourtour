package net.nigne.yourtour.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.exception.NotFoundException;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auther {

    private String auther;

    public Auther(String auther) {
        this.auther = validation(auther);
    }

    private String validation(String auther) {
        if(StringUtils.isEmpty(auther)) {
            throw new NotFoundException("저자정보가 없습니다.");
        }
        return auther;
    }
}
