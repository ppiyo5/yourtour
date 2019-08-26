package net.nigne.yourtour.user.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.user.application.SellerService;
import net.nigne.yourtour.user.application.dto.SellerCreateDto;
import net.nigne.yourtour.user.application.dto.SellerLoginDto;
import net.nigne.yourtour.user.infra.SellerTranslate;
import net.nigne.yourtour.user.ui.dto.SellerResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping(value = "/seller")
    public ApiResponse<SellerResponseDto> createSeller(@Valid @RequestBody SellerCreateDto createDto) {
        return ApiResponse.createOK(SellerTranslate.translate(sellerService.createSeller(createDto)));
    }

    @GetMapping(value = "/seller/{id}")
    public ApiResponse<SellerResponseDto> findSeller(@PathVariable String id) {
        return ApiResponse.createOK(SellerTranslate.translate(sellerService.findById(id)));
    }

    @PostMapping(value = "/seller/login")
    public ApiResponse<SellerResponseDto> loginSeller(@Valid @RequestBody SellerLoginDto loginDto) {
        return ApiResponse.createOK(SellerTranslate.translate(sellerService.loginSeller(loginDto)));
    }
}
