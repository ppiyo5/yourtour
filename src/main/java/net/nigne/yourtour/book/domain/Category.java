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
public class Category {

    private String category;

    public Category(String category) {
        this.category = validation(category);
    }

    private String validation(String category) {

        if(StringUtils.isEmpty(category)) {
            throw new NotFoundException("카테고리가 없습니다.");
        }

        return category;
    }
}
