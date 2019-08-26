package net.nigne.yourtour.user.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerResponseDto {

    private String id;

    private String name;

    private String address;

    private String email;

    private String phoneNumber;

    @Builder(builderMethodName = "responseBuilder")
    private SellerResponseDto(String id, String name, String address, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
