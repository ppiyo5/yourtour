package net.nigne.yourtour.book.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nigne.yourtour.book.domain.BookRepository;
import net.nigne.yourtour.book.infra.dto.BookApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookApiComponent {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    @Value("${api.host}")
    String host;

    @Value("${api.client.id}")
    String clientId;

    @Value("${api.client.secret}")
    String clientSecret;

    public void save(String category) {
        ResponseEntity<BookApiResponseDto> response = execute(category);
        response.getBody().getItems()
                .forEach(item -> saveItem(item, category));
    }

    private void saveItem(BookApiResponseDto.Item item, String category) {
        bookRepository.save(BookTran)
    }

    public ResponseEntity<BookApiResponseDto> execute(String category) {
        HttpEntity request = new HttpEntity<>(setHeader());
        String apiUrl = generateURL(category);
        ResponseEntity<BookApiResponseDto> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, BookApiResponseDto.class);
        log.info("response : {} ", response);
        return response;
    }

    private String generateURL(String category) {
        return host +
                "/v1/search/book.json?query="
                + category;
    }

    private HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        return headers;
    }
}
