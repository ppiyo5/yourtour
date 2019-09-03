package net.nigne.yourtour.enrollment.ui.dto;

import net.nigne.yourtour.enrollment.application.dto.EventDto;
import net.nigne.yourtour.exception.WrongValueException;

import java.time.LocalDate;

public class EventDtoTranslate {

    public static EventDto translate(EventRequestDto requestDto) {
        LocalDate startDate = requestDto.getStartDate();
        LocalDate endDate = requestDto.getEndDate();
        Double periodPercent = requestDto.getPeriodPercent();

        validateDate(startDate, endDate);
        validatePercent(periodPercent);
        return new EventDto(startDate, endDate, periodPercent);
    }

    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new WrongValueException("잘못된 기간설정입니다.");
        }
    }

    private static void validatePercent(Double periodPercent) {
        if(periodPercent < 0) {
            throw new WrongValueException("잘못된 할인율입니다.");
        }
    }
}
