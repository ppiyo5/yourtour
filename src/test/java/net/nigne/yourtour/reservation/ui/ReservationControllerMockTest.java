package net.nigne.yourtour.reservation.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nigne.yourtour.exception.MaxOverReservationException;
import net.nigne.yourtour.reservation.application.ReservationService;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.response.ApiResponseCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.thymeleaf.spring5.expression.Mvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ReservationController.class})
public class ReservationControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void 실패_생성요청_파라미터없음() throws Exception {

        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/reservation")
                .header("user-id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("파라미터가 존재하지 않습니다.");
    }

    @Test
    public void 실패_생성요청_파라미터비어있음() throws Exception {

        //given
        String requestBody = "{\"bookId\":\"\"}";

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/reservation")
                .header("user-id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("요청 파라미터가 잘못되었습니다.");

    }

    @Test
    public void 실패_생성요청_파라미터타입이_다름() throws Exception {

        //given
        String requestBody = "{\"bookId\":\"ㄱㄴ\"}";

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/reservation")
                .header("user-id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("[bookId] 타입은 Long 이여야 합니다. 실제값: ㄱㄴ");
    }

    @Test
    public void 실패_15권_이상_찜_생성요청() throws Exception {

        //given
        String requestBody = "{\"bookId\":1}";

        willThrow(new MaxOverReservationException(String.format("최대 %s권을 찜할 수 있습니다.", 15)))
                .given(reservationService)
                .add(anyString(), anyLong());

        //when
        MvcResult mvcResult  =this.mockMvc.perform(post("/reservation")
                .header("user-id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("최대 15권을 찜할 수 있습니다.");
    }

    @Test
    public void 성공_생성요청() throws Exception {

        //given
        String requestBody = "{\"bookId\":1}";

        //when
        MvcResult mvcResult  =this.mockMvc.perform(post("/reservation")
                .header("user-id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");

    }



    private ApiResponse getApiResponse(MvcResult mvcResult) throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}