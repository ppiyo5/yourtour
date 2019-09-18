package net.nigne.yourtour.user.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberLoginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
