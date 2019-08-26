package net.nigne.yourtour.user.infra;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.user.application.dto.BuyerCreateDto;
import net.nigne.yourtour.user.domain.Buyer;
import net.nigne.yourtour.user.ui.dto.BuyerResponseDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerTranslate {

    public static Buyer translate(BuyerCreateDto buyerRequestDto) {

        return Buyer.createBuilder()
                .id(buyerRequestDto.getId())
                .password(buyerRequestDto.getPassword())
                .name(buyerRequestDto.getName())
                .phoneNumber(buyerRequestDto.getPhoneNumber())
                .email(buyerRequestDto.getEmail())
                .address(buyerRequestDto.getAddress())
                .build();
    }

    public static BuyerResponseDto translate(Buyer buyer) {

        return BuyerResponseDto.responseBuilder()
                .id(buyer.getId())
                .name(buyer.getUser().getName().getName())
                .phoneNumber(buyer.getUser().getPhoneNumber().getPhoneNumber())
                .email(buyer.getUser().getEmail().getEmail())
                .address(buyer.getUser().getAddress().getAddress())
                .build();
    }
}
