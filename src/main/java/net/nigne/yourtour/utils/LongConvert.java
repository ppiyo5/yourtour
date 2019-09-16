package net.nigne.yourtour.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.exception.TypeMissMatchException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LongConvert {

    public static Long valueOf(String fieldName, String value) {

        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new TypeMissMatchException(String.format("[%s] 타입은 Long 이여야 합니다. 실제값: %s", fieldName, value));
        }
    }
}
