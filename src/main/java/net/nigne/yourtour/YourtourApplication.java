package net.nigne.yourtour;

import net.nigne.yourtour.book.infra.BookApiComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class YourtourApplication {

    public static void main(String[] args) {

        final ApplicationContext context = SpringApplication.run(YourtourApplication.class, args);

        final BookApiComponent component = context.getBean(BookApiComponent.class);
        component.save("IT");
        component.save("소설");
        component.save("인문");
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(300000);
        factory.setConnectionRequestTimeout(300000);
        return new RestTemplate(factory);
    }

}
