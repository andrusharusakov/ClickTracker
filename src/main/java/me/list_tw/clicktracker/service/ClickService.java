package me.list_tw.clicktracker.service;

import jakarta.servlet.http.HttpServletRequest;
import me.list_tw.clicktracker.model.ClickRecord;
import me.list_tw.clicktracker.model.PathStatistics;
import me.list_tw.clicktracker.repository.ClickRecordRepository;
import me.list_tw.clicktracker.repository.PathStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClickService {

    @Autowired
    private ClickRecordRepository clickRecordRepository;

    @Autowired
    private PathStatisticsRepository pathStatisticsRepository;

    public void recordClick(HttpServletRequest request, String path) {
        String ipAddress = request.getRemoteAddr();
        String region = request.getHeader("X-Region"); // Replace with real region extraction logic
        String device = request.getHeader("User-Agent"); // Replace with real device extraction logic
        String browser = request.getHeader("User-Agent"); // You can parse this for better information

        ClickRecord clickRecord = new ClickRecord();
        clickRecord.setIpAddress(ipAddress);
        clickRecord.setRegion(region);
        clickRecord.setDevice(device);
        clickRecord.setBrowser(browser);
        clickRecord.setPath(path);

        clickRecordRepository.save(clickRecord);

        PathStatistics stats = pathStatisticsRepository.findByPath(path);
        if (stats == null) {
            stats = new PathStatistics();
            stats.setPath(path);
            stats.setTotalClicks(1L);
            stats.setUniqueClicks(1L);
        } else {
            stats.setTotalClicks(stats.getTotalClicks() + 1);
            // Logic for unique clicks based on IP address would go here
            stats.setUniqueClicks(stats.getUniqueClicks() + 1); // For simplicity, counting all as unique
        }
        pathStatisticsRepository.save(stats);
    }
}
