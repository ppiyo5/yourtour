package net.nigne.yourtour.reservation.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.reservation.application.ReservationService;
import net.nigne.yourtour.reservation.application.dto.ReservationCreateDto;
import net.nigne.yourtour.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ApiResponse add(@RequestHeader("user-id") String buyerId, @RequestBody @Valid ReservationCreateDto createDto) {

        reservationService.add(buyerId, createDto.getRegisteredBookId());

        return ApiResponse.createOK();
    }

    @DeleteMapping("{bookId}")
    public ApiResponse delete(@RequestHeader("user-id") String buyerId, @PathVariable("bookId") Long bookId) {

        reservationService.delete(buyerId, bookId);

        return ApiResponse.createOK();
    }
}
