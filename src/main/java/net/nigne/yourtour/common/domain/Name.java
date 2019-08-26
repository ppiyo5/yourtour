package net.nigne.yourtour.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.exception.NotFoundException;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    private String name;

    public Name(String name) {
        this.name = validation(name);

    }

    private String validation(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new NotFoundException("이름이 없습니다.");
        }
        return name;
    }
}
