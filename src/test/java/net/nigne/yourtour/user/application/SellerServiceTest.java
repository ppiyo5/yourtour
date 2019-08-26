package net.nigne.yourtour.user.application;

import net.nigne.yourtour.user.domain.Seller;
import net.nigne.yourtour.user.domain.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {

    @Mock
    private SellerRepository repository;

    @InjectMocks
    private SellerService service;

    @Test
    public void id로_구매자_조회() {
        //given
        given(repository.findById(any()))
                .willReturn(
                        Optional.of(
                                Seller.createBuilder()
                                        .id("rosie")
                                        .password("1234")
                                        .name("로지")
                                        .address("강남구")
                                        .email("rosie@daum.net")
                                        .phoneNumber("010-1122-3344")
                                        .build()
                        )
                )
        ;

        Seller seller = Seller.createBuilder()
                .id("rosie")
                .name("로지")
                .password("1234")
                .address("강남구")
                .email("rosie@daum.net")
                .phoneNumber("010-1122-3344")
                .build();

        //when
        Seller seller1 = service.findById("rosie");

        //then
        assertThat(seller1.getUser().getName().getName()).isEqualTo(seller.getUser().getName().getName());

    }

}