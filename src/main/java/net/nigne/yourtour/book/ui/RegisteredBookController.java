package net.nigne.yourtour.book.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nigne.yourtour.book.application.RegisteredBookService;
import net.nigne.yourtour.book.ui.dto.*;
import net.nigne.yourtour.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/registered-books")
@RequiredArgsConstructor
public class RegisteredBookController {

    private final RegisteredBookService registeredBookService;

    @PostMapping("/new")
    public ApiResponse save(HttpSession session, @RequestHeader("user-id") String sellerId, @Valid @RequestBody NewBookEnrollmentRequest newBookEnrollmentRequest) {
        registeredBookService.save(session, RegisteredBookRequestTranslate.translate(sellerId, newBookEnrollmentRequest));
        return ApiResponse.createOK();
    }

    @PostMapping("/used")
    public ApiResponse save(HttpSession session, @RequestHeader("user-id") String sellerId, @Valid @RequestBody UsedBookEnrollmentRequest usedBookEnrollmentRequest) {
        registeredBookService.save(session, RegisteredBookRequestTranslate.translate(sellerId, usedBookEnrollmentRequest));
        return ApiResponse.createOK();
    }

    @GetMapping("")
    public ApiResponse findBy(PageOptions pageOptions) {
        return ApiResponse.createOK(registeredBookService.findBy(PageConverter.convert(pageOptions)));
    }
}
