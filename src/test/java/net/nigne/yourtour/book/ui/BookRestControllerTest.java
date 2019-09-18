package net.nigne.yourtour.book.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import net.nigne.yourtour.book.domain.Book;
import net.nigne.yourtour.book.domain.BookRepository;
import net.nigne.yourtour.book.ui.dto.BookResponseDto;
import net.nigne.yourtour.response.ApiResponse;
import net.nigne.yourtour.response.ApiResponseCode;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log
public class BookRestControllerTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    @Test
    public void getBook_테스트() throws Exception {

        //given
        createBook();
        MvcResult mvcResult = this.mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //when
        ApiResponse apiResponse = getApiResponse(mvcResult);

        //then
        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);

    }

    private ApiResponse getApiResponse(MvcResult mvcResult) throws IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

    public void createBook() {
        bookRepository.save(Book.createBuilder()
                .name("이펙티브 자바 (Effective Java)")
                .author("조슈아 블로크")
                .category("IT")
                .publisher("인사이트")
                .price(10000L)
                .build());
    }

}