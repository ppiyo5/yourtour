package net.nigne.yourtour.user.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.error.ErrorCode;
import net.nigne.yourtour.exception.AlreadyReservationException;
import net.nigne.yourtour.exception.BusinessException;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.user.application.dto.MemberCreateDto;
import net.nigne.yourtour.user.application.dto.MemberLoginDto;
import net.nigne.yourtour.user.application.dto.MemberUpdateDto;
import net.nigne.yourtour.user.domain.Member;
import net.nigne.yourtour.user.domain.MemberRepository;
import net.nigne.yourtour.user.infra.MemberTranslate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(MemberCreateDto createDto) {

        if(memberRepository.findById(createDto.getId()).isPresent()) {
            throw new AlreadyReservationException("이미 존재하는 아이디입니다.");
        }

        Member member = MemberTranslate.translate(createDto);
        return memberRepository.save(member);
    }

    public Member findMember(String id) {

        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s], 존재하지 않는 아이디입니다.", id)));
    }

    @Transactional
    public Member updateMember(String id, MemberUpdateDto updateDto) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s], 존재하지 않는 아이디입니다.", id)));

        member.update(updateDto.getName(), updateDto.getPassword(), updateDto.getPhoneNumber(), updateDto.getEmail(), updateDto.getAddress());
        return member;
    }

    public Member loginMember(MemberLoginDto loginDto) {

        Member member = memberRepository.findById(loginDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("[%s], 존재하지 않는 아이디입니다.", loginDto.getId())));

        if(member.getUser().getPassword().equals(loginDto.getPassword())) {
            return member;
        }

        throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED, "비밀번호가 일치하지 않습니다.");
    }
}
