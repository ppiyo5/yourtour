package net.nigne.yourtour.book.application;

import net.nigne.yourtour.book.application.dto.RegisteredBookDto;
import net.nigne.yourtour.book.application.dto.RegisteredBookRequestDto;
import net.nigne.yourtour.common.HttpSessionUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class RegisteredBookSessionManager {

    public void addNewRegisteredBook(HttpSession session, RegisteredBookRequestDto dto) {
        List<RegisteredBookDto> registeredBookDtos = HttpSessionUtil.getSessionRegisteredBook(session);
        for(int i = 0; i < dto.getCount(); i++) {
            addRegisteredBook(registeredBookDtos, dto);
        }
        HttpSessionUtil.setSessionRegisteredBook(session, registeredBookDtos);
    }

    public void addUsedRegisteredBook(HttpSession session, RegisteredBookRequestDto dto) {
        List<RegisteredBookDto> registeredBookDtos = HttpSessionUtil.getSessionRegisteredBook(session);
        addRegisteredBook(registeredBookDtos, dto);

    }

    private void addRegisteredBook(List<RegisteredBookDto> registeredBookDtos, RegisteredBookRequestDto dto) {
        registeredBookDtos.add(dto.toSession());
    }
}
