package net.nigne.yourtour.user.ui;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.user.application.MemberService;
import net.nigne.yourtour.user.application.dto.MemberCreateDto;
import net.nigne.yourtour.user.application.dto.MemberLoginDto;
import net.nigne.yourtour.user.application.dto.MemberUpdateDto;
import net.nigne.yourtour.user.infra.MemberTranslate;
import net.nigne.yourtour.user.ui.dto.MemberResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //멤버 생성
    @PostMapping(value = "/member")
    public ApiResponse<MemberResponseDto> createMember(@Valid @RequestBody MemberCreateDto createDto) {
        return ApiResponse.createOK(MemberTranslate.translate(memberService.createMember(createDto)));
    }

    //멤버 조회
    @GetMapping(value = "/member/{id}")
    public ApiResponse<MemberResponseDto> findMember(@PathVariable String id) {
        return ApiResponse.createOK(MemberTranslate.translate(memberService.findMember(id)));
    }

    //멤버 업데이트
    @PutMapping(value = "/member")
    public ApiResponse<MemberResponseDto> updateMember(@RequestHeader("user-id") String id, @Valid @RequestBody MemberUpdateDto updateDto) {
        return ApiResponse.createOK(MemberTranslate.translate(memberService.updateMember(id, updateDto)));
    }

    //멤버 업데이트
    @PutMapping(value = "/member/{id}")
    public ApiResponse<MemberResponseDto> updateMember2(@PathVariable String id, @Valid @RequestBody MemberUpdateDto updateDto) {
        return ApiResponse.createOK(MemberTranslate.translate(memberService.updateMember(id, updateDto)));
    }

    //멤버 로그인
    @PostMapping(value = "/member/login")
    public ApiResponse<MemberResponseDto> loginMember(@Valid @RequestBody MemberLoginDto loginDto) {
        return ApiResponse.createOK(MemberTranslate.translate(memberService.loginMember(loginDto)));
    }

}
