package ru.topjava.graduation.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RootController implements Controller {

    @RequestMapping("/")
    void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect(RESTAURANTS_URL);
    }
}