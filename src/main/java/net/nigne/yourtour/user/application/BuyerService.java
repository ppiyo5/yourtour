package net.nigne.yourtour.user.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.error.ErrorCode;
import net.nigne.yourtour.exception.AlreadyReservationException;
import net.nigne.yourtour.exception.BusinessException;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.user.application.dto.BuyerCreateDto;
import net.nigne.yourtour.user.application.dto.BuyerLoginDto;
import net.nigne.yourtour.user.application.dto.BuyerUpdateDto;
import net.nigne.yourtour.user.domain.Buyer;
import net.nigne.yourtour.user.domain.BuyerRepository;
import net.nigne.yourtour.user.infra.BuyerTranslate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public Buyer createBuyer(BuyerCreateDto createDto) {
        if (buyerRepository.findById(createDto.getId()).isPresent()) {
            throw new AlreadyReservationException("이미 존재하는 아이디입니다.");
        }

        Buyer buyer = BuyerTranslate.translate(createDto);
        return buyerRepository.save(buyer);
    }

    public Buyer findById(String id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 구매자 입니다.", id)));
    }

    public Buyer updateBuyer(String id, BuyerUpdateDto updateDto) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 구매자 입니다.", id)));

        buyer.update(updateDto.getName(), updateDto.getPassword(), updateDto.getPhoneNumber(), updateDto.getEmail(), updateDto.getAddress());
        return buyer;
    }

    public Buyer loginBuyer(BuyerLoginDto loginDto) {
        Buyer buyer = buyerRepository.findById(loginDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 아이디 입니다.", loginDto.getId())));

        if(buyer.getUser().getPassword().equals(loginDto.getPassword())) {
            return buyer;
        }

        throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED, "비밀번호가 일치하지 않습니다.");
    }
}
