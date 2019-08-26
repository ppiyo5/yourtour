package net.nigne.yourtour.user.infra;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.user.application.dto.SellerCreateDto;
import net.nigne.yourtour.user.domain.Seller;
import net.nigne.yourtour.user.ui.dto.SellerResponseDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerTranslate {

    public static Seller translate(SellerCreateDto sellerRequestDto) {
        return Seller.createBuilder()
                .id(sellerRequestDto.getId())
                .name(sellerRequestDto.getName())
                .password(sellerRequestDto.getPassword())
                .email(sellerRequestDto.getEmail())
                .phoneNumber(sellerRequestDto.getPhoneNumber())
                .address(sellerRequestDto.getAddress())
                .build();
    }

    public static SellerResponseDto translate(Seller seller) {

        return SellerResponseDto.responseBuilder()
                .id(seller.getId())
                .name(seller.getUser().getName().getName())
                .email(seller.getUser().getEmail().getEmail())
                .phoneNumber(seller.getUser().getPhoneNumber().getPhoneNumber())
                .address(seller.getUser().getAddress().getAddress())
                .build();
    }
}
