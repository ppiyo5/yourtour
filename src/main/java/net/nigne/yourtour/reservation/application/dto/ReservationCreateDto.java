package net.nigne.yourtour.reservation.application.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.nigne.yourtour.utils.LongConvert;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationCreateDto {

    @Setter(AccessLevel.PRIVATE)
    @NotBlank
    private String bookId;

    public Long getRegisteredBookId() {
        return LongConvert.valueOf("bookId", bookId);
    }
}
