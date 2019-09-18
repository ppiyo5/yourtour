package net.nigne.yourtour.book.infra;

import net.nigne.yourtour.book.infra.dto.BookApiResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookApiComponentTest {

    @Autowired
    private BookApiComponent component;

    @Test
    public void bookApiTest() throws Exception {
        ResponseEntity<BookApiResponseDto> response = component.execute("IT");

        assertThat(Objects.requireNonNull(response.getBody()).getItems().size()).isEqualTo(10);
    }

}