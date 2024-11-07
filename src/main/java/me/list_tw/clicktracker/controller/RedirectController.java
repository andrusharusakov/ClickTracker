package me.list_tw.clicktracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.list_tw.clicktracker.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class RedirectController {

    @Autowired
    private ClickService clickService;

    @GetMapping("/{promoId}")
    public void redirect(@PathVariable String promoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = promoId;
        if (!path.equals("styles.css")) {
            clickService.recordClick(request, path);
        }
        response.sendRedirect("https://leafcity.ru");
    }
}
