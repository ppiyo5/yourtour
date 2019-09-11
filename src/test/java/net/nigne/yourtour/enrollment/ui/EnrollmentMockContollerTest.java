package net.nigne.yourtour.enrollment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nigne.yourtour.enrollment.application.EnrollmentService;
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

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {EnrollmentContoller.class})
public class EnrollmentMockContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService;

    @Test
    public void 이벤트_날짜없이_등록요청() throws Exception {
        String requestBody = "{\"startDate\":\"2019-05-08\", \"periodPercent\":20.0}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "rosie")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("요청 파라미터가 잘못되었습니다.");
    }

    @Test
    public void 이벤트_시작날짜보다_끝나는날짜가_더빠른_등록요청() throws Exception {
        String requestBody = "{\"startDate\":\"2019-05-08\", \"endDate\":\"2019-05-07\", \"periodPercent\":20.0}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "rosie")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("잘못된 기간설정입니다.");
    }

    @Test
    public void 할인율_0프로이하_등록요청() throws Exception {
        String requestBody = "{\"startDate\":\"2019-05-07\", \"endDate\":\"2019-05-08\", \"periodPercent\":-1}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "rosie")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("잘못된 할인율입니다.");
    }

    @Test
    public void 등록_성공() throws Exception {
        String requestBody = "{\"startDate\":\"2019-05-07\", \"endDate\":\"2019-05-08\", \"periodPercent\":20.0}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "rosie")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");
    }

    ApiResponse getApiResponse(MvcResult mvcResult) throws IOException {
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}