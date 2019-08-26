package net.nigne.yourtour.user.application;

import lombok.RequiredArgsConstructor;
import net.nigne.yourtour.exception.AlreadyReservationException;
import net.nigne.yourtour.exception.NotFoundException;
import net.nigne.yourtour.user.application.dto.SellerCreateDto;
import net.nigne.yourtour.user.domain.Seller;
import net.nigne.yourtour.user.domain.SellerRepository;
import net.nigne.yourtour.user.infra.SellerTranslate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller createSeller(SellerCreateDto createDto) {
        if(sellerRepository.findById(createDto.getId()).isPresent()) {
            throw new AlreadyReservationException("이미 존재하는 아이디입니다.");
        }
        Seller seller = SellerTranslate.translate(createDto);
        return sellerRepository.save(seller);
    }

    public Seller findById(String id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 판매자입니다.", id)));
    }
}
