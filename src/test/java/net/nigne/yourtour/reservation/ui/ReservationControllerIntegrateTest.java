package net.nigne.yourtour.reservation.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nigne.yourtour.book.domain.RegisteredBook;
import net.nigne.yourtour.book.domain.RegisteredBookRepository;
import net.nigne.yourtour.common.domain.UnitAmount;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.response.ApiResponseCode;
import net.nigne.yourtour.user.domain.Buyer;
import net.nigne.yourtour.user.domain.BuyerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerIntegrateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private RegisteredBookRepository registeredBookRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void create() {
         buyerRepository.save(
                Buyer.createBuilder()
                        .id("test")
                        .password("1234")
                        .name("한소진")
                        .phoneNumber("010-1123-1111")
                        .email("email@email.com")
                        .address("서울")
                        .build()
        );
    }

    @After
    public void tearDown() throws Exception {
        registeredBookRepository.deleteAll();
        buyerRepository.deleteAll();
    }

    @Test
    public void 성공_생성요청() throws Exception {

        //given
        String requestBody = "{\"bookId\":1}";
        Buyer buyer = createBuyer();
        createRegisterBook();

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/reservation")
                .header("user-id", buyer.getId())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");
    }

    private Buyer createBuyer() {
        return buyerRepository.save(
                Buyer.createBuilder()
                .id("test")
                .password("1234")
                .name("한소진")
                .phoneNumber("010-1123-1111")
                .email("email@email.com")
                .address("서울")
                .build()
        );
    }

    private RegisteredBook createRegisterBook() {
        return registeredBookRepository.save(
                RegisteredBook.builder()
                        .bookId(1L)
                        .amount(new UnitAmount(10000L))
                        .build()
        );
    }

    private ApiResponse getApiResponse(MvcResult mvcResult) throws IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}