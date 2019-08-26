package net.nigne.yourtour.user.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.user.application.BuyerService;
import net.nigne.yourtour.user.application.dto.BuyerCreateDto;
import net.nigne.yourtour.user.application.dto.BuyerLoginDto;
import net.nigne.yourtour.user.application.dto.BuyerUpdateDto;
import net.nigne.yourtour.user.infra.BuyerTranslate;
import net.nigne.yourtour.user.ui.dto.BuyerResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping(value = "/buyer/{id}")
    public ApiResponse<BuyerResponseDto> findBuyer(@PathVariable String id) {
        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.findById(id)));
    }

    @PostMapping(value = "/buyer")
    public ApiResponse<BuyerResponseDto> createBuyer(@Valid @RequestBody BuyerCreateDto createDto) {
        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.createBuyer(createDto)));
    }

    @PutMapping(value = "/buyer")
    public ApiResponse<BuyerResponseDto> updateBuyer(@RequestHeader("user-id") String id, @Valid @RequestBody BuyerUpdateDto updateDto) {
        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.updateBuyer(id, updateDto)));
    }

    @PostMapping(value = "/buyer/login")
    public ApiResponse<BuyerResponseDto> loginBuyer(@Valid @RequestBody BuyerLoginDto loginDto) {
        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.loginBuyer(loginDto)));
    }
}
