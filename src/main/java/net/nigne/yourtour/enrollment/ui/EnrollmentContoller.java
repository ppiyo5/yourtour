package net.nigne.yourtour.enrollment.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.common.HttpSessionUtil;
import net.nigne.yourtour.enrollment.application.EnrollmentService;
import net.nigne.yourtour.enrollment.application.dto.EnrollmentDto;
import net.nigne.yourtour.enrollment.ui.dto.EventDtoTranslate;
import net.nigne.yourtour.enrollment.ui.dto.EventRequestDto;
import net.nigne.yourtour.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentContoller {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ApiResponse save(HttpSession session, @RequestHeader("user-id") String sellerId, @RequestBody @Valid EventRequestDto requestDto) {
        enrollmentService.save(EnrollmentDto.of(sellerId, EventDtoTranslate.translate(requestDto)),
                HttpSessionUtil.getSessionRegisteredBook(session));

        return ApiResponse.createOK();
    }
}
