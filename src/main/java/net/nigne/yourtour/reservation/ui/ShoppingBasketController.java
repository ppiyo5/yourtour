package net.nigne.yourtour.reservation.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.reservation.application.ShoppingBasketService;
import net.nigne.yourtour.reservation.application.dto.ShoppingBasketResponseDto;
import net.nigne.yourtour.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("shopping-basket")
@RestController
@RequiredArgsConstructor
public class ShoppingBasketController {

    private final ShoppingBasketService shoppingBasketService;

    @GetMapping
    public ApiResponse<ShoppingBasketResponseDto> findShoppingBasket(@RequestHeader("user-id") String buyerId) {

        return ApiResponse.createOK(shoppingBasketService.findShoppingBasket(buyerId));
    }
}
