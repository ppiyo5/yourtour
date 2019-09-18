package net.nigne.yourtour.user.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    private String addess;

    @Builder(builderMethodName = "responseBuilder")
    public MemberResponseDto(String id, String name, String phoneNumber, String email, String addess) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addess = addess;
    }
}
