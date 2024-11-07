package me.list_tw.clicktracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.list_tw.clicktracker.model.PathStatistics;
import me.list_tw.clicktracker.repository.PathStatisticsRepository;
import me.list_tw.clicktracker.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class RedirectController {

    @Autowired
    private ClickService clickService;

    @GetMapping("/{promoId}")
    public void redirect(@PathVariable String promoId, HttpServletRequest request, HttpServletResponse response) {
        String path = "/promo/" + promoId;
        clickService.recordClick(request, path);
        try {
            response.sendRedirect("https://leafcity.ru");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private PathStatisticsRepository pathStatisticsRepository;

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        List<PathStatistics> statistics = pathStatisticsRepository.findAll();
        model.addAttribute("statistics", statistics);
        return "statistics";
    }
}
