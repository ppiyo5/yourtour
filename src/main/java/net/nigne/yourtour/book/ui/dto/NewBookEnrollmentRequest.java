package net.nigne.yourtour.book.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewBookEnrollmentRequest {

    @NotNull
    private Long bookId;

    @Min(1)
    @NotNull
    private Long count;

    @Builder(builderMethodName = "createBuilder")
    public NewBookEnrollmentRequest(Long bookId, Long count) {
        this.bookId = bookId;
        this.count = count;
    }
}
