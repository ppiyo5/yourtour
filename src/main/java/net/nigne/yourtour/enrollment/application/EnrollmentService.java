package net.nigne.yourtour.enrollment.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.book.application.RegisteredBookService;
import net.nigne.yourtour.book.application.dto.RegisteredBookDto;
import net.nigne.yourtour.enrollment.application.dto.EnrollmentDto;
import net.nigne.yourtour.enrollment.application.dto.EventDto;
import net.nigne.yourtour.enrollment.domain.Enrollment;
import net.nigne.yourtour.enrollment.domain.EnrollmentRepository;
import net.nigne.yourtour.enrollment.domain.Event;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.exception.WrongValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final RegisteredBookService registeredBookService;

    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 등록이 존재하지 않습니다", id)));
    }

    public void save(EnrollmentDto enrollmentDto, List<RegisteredBookDto> registeredBookDtos) {
        validateRegisterBook(enrollmentDto.getSellerId(), registeredBookDtos);
        Enrollment enrollment = enrollmentRepository.save(convert(enrollmentDto));
        registeredBookService.save(enrollment.getId(), registeredBookDtos);
    }

    private void validateRegisterBook(String sellerId, List<RegisteredBookDto> registeredBookDtos) {
        if(registeredBookDtos.isEmpty()) {
            throw new WrongValueException("등록된 책이 없습니다.");
        }

        registeredBookDtos.forEach(registeredBookDto -> {
            if(!registeredBookDto.getSellerId().equals(sellerId)) {
                throw new WrongValueException("로그인한 유저와 등록한 유저가 다릅니다.");
            }
        });
    }

    private Enrollment convert(EnrollmentDto dto) {
        Event event = convert(dto.getEventDto());
        return new Enrollment(dto.getSellerId(), Collections.singletonList(event));
    }

    private Event convert(EventDto dto) {
        return new Event(dto.getStartDate(), dto.getEndDate(), dto.getPeriodPercent());
    }
}
