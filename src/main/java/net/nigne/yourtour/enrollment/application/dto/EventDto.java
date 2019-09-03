package net.nigne.yourtour.enrollment.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.enrollment.domain.Event;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private Double periodPercent;

    public EventDto(LocalDate startDate, LocalDate endDate, Double periodPercent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodPercent = periodPercent;
    }

    public Event toEntity() {
        return new Event(startDate, endDate, periodPercent);
    }


}
