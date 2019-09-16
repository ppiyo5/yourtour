package net.nigne.yourtour.enrollment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.nigne.yourtour.book.domain.BookRepository;
import net.nigne.yourtour.enrollment.domain.EnrollmentRepository;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.response.ApiResponseCode;
import org.junit.After;
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
public class EnrollmentContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책_DB_삭제() {
        bookRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        enrollmentRepository.deleteAll();;
    }

    @Test
    public void 등록된_책없이_저장시도() throws Exception {
        //given
        String reqeustBody = "{\"startDate\":\"2019-05-08\", \"endDate\":\"2019-05-09\", \"periodPercent\":10}";

        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(reqeustBody)
                .header("user-id", "rosie")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("등록된 책이 없습니다.");

    }

    ApiResponse getApiResponse(MvcResult mvcResult) throws IOException {
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}