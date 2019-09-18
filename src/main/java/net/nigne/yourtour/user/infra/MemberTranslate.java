package net.nigne.yourtour.user.infra;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.user.application.dto.MemberCreateDto;
import net.nigne.yourtour.user.domain.Member;
import net.nigne.yourtour.user.ui.dto.MemberResponseDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberTranslate {

    public static Member translate(MemberCreateDto requestDto) {
        return Member.createBuilder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .build();
    }

    public static MemberResponseDto translate(Member member) {
        return MemberResponseDto.responseBuilder()
                .id(member.getId())
                .name(member.getUser().getName().getName())
                .phoneNumber(member.getUser().getPhoneNumber().getPhoneNumber())
                .email(member.getUser().getEmail().getEmail())
                .addess(member.getUser().getAddress().getAddress())
                .build();
    }
}
