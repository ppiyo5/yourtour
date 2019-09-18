package net.nigne.yourtour.user.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateDto {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Pattern(regexp = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    @Email(regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank
    private String address;

}
