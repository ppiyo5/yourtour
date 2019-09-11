package net.nigne.yourtour.book.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.BookRepository;
import net.nigne.yourtour.book.ui.dto.NewBookEnrollmentRequest;
import net.nigne.yourtour.book.ui.dto.UsedBookEnrollmentRequest;
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
public class RegisteredBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    @Test
    public void 새책_성공_생성요청() throws Exception {

        //given
        Book book = createBook();
        NewBookEnrollmentRequest requestBody = createNewBookBody(book);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertString(requestBody))
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

    @Test
    public void 중고책_성공_생성요청() throws Exception {

        //given
        Book book = createBook();
        UsedBookEnrollmentRequest requestBody = createUsedBookBody(book);

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/used")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertString(requestBody))
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

    private ApiResponse getApiResponse(MvcResult mvcResult) throws IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

    private Book createBook() {
        Book book = Book.createBuilder()
                .name("자바")
                .author("토비")
                .category("IT")
                .publisher("한빛")
                .price(10000L)
                .build();

        bookRepository.save(book);
        return bookRepository.findAll().get(0);
    }

    private NewBookEnrollmentRequest createNewBookBody(Book book) {
        return NewBookEnrollmentRequest.createBuilder()
                .bookId(book.getId())
                .count(10L)
                .build();
    }

    private UsedBookEnrollmentRequest createUsedBookBody(Book book) {
        return UsedBookEnrollmentRequest.builder()
                .bookId(book.getId())
                .amount(10000L)
                .build();
    }

    private String convertString(Object body) throws JsonProcessingException {
        return objectMapper.writeValueAsString(body);
    }

}