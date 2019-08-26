package net.nigne.yourtour.main.ui;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class MainController {

    @GetMapping("/")
    public String main() {
        log.info("main page start()");
        return "hello";
    }

    @GetMapping("/hello")
    public void hello() {
        log.info("Hello World!");
    }

}
