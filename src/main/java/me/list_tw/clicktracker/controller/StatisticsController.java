package me.list_tw.clicktracker.controller;

import me.list_tw.clicktracker.model.PathStatistics;
import me.list_tw.clicktracker.repository.PathStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private PathStatisticsRepository pathStatisticsRepository;

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        List<PathStatistics> statistics = pathStatisticsRepository.findAll();
        model.addAttribute("statistics", statistics);
        return "statistics";
    }
}
