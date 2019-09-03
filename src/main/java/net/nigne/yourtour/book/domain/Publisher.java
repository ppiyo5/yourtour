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
public class Publisher {

    private String publisher;

    public Publisher(String publisher) {
        this.publisher = validation(publisher);
    }

    private String validation(String publisher) {

        if(StringUtils.isEmpty(publisher)) {
            throw new NotFoundException("출판사 정보가 없습니다.");
        }

        return publisher;
    }
}
