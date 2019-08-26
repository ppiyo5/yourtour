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
public class BuyerRepositoryTest {

    @Autowired
    private BuyerRepository repository;

    @Test
    public void Buyer_조회() {

        //given
        Buyer buyer = createBuyer();

        //when
        Optional<Buyer> resultBuyer = repository.findById("test");

        //then
        assertThat(resultBuyer.get().getId()).isEqualTo(buyer.getId());
    }

    public Buyer createBuyer() {
        return repository.save(Buyer.createBuilder()
                .id("test")
                .password("1234")
                .name("한소진")
                .phoneNumber("010-1234-5678")
                .email("ppiyo@naver.com")
                .address("반포동")
                .build());
    }

}