package net.nigne.yourtour.user.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void seller_조회() {
        //given
        Seller seller = createSeller();

        //when
        Optional<Seller> resultSeller = sellerRepository.findById("rosie");

        //then
        assertThat(resultSeller.get().getId()).isEqualTo(seller.getId());

    }

    public Seller createSeller() {
        return sellerRepository.save(Seller.createBuilder()
                .id("rosie")
                .name("로지")
                .password("1234")
                .address("경기도 남양주시")
                .email("rosie@daum.net")
                .phoneNumber("010-1122-3344")
                .build()
        );
    }

}