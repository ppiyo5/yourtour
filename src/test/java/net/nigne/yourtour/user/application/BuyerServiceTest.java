package net.nigne.yourtour.user.application;

import net.nigne.yourtour.user.domain.Buyer;
import net.nigne.yourtour.user.domain.BuyerRepository;
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
public class BuyerServiceTest {

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private BuyerService buyerService;

    @Test
    public void id로_구매자_조회() {
        //given
        given(buyerRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                Buyer.createBuilder()
                                .id("user")
                                .name("김유저")
                                .password("1234")
                                .phoneNumber("010-1234-5678")
                                .email("user@naver.com")
                                .address("서울시")
                                .build()
                        )
                )
        ;

        Buyer buyer = Buyer.createBuilder()
                .id("user")
                .name("김유저")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .email("user@naver.com")
                .address("서울시")
                .build();

        //when
        Buyer buyer1 = buyerService.findById("user");

        //then
        assertThat(buyer1.getUser().getName().getName()).isEqualTo(buyer.getUser().getName().getName());
    }

}