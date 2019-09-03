package net.nigne.yourtour.enrollment.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.enrollment.domain.Enrollment;

import java.util.Collections;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentDto {

    private String sellerId;

    private EventDto eventDto;

    @Builder

    public EnrollmentDto(String sellerId, EventDto eventDto) {
        this.sellerId = sellerId;
        this.eventDto = eventDto;
    }

    public static EnrollmentDto of(String sellerId, EventDto eventDto) {
        return EnrollmentDto.builder()
                .sellerId(sellerId)
                .eventDto(eventDto)
                .build();
    }

    public Enrollment toEntity() {
        return new Enrollment(sellerId, Collections.singletonList(eventDto.toEntity()));
    }
}
