package me.list_tw.clicktracker.service;

import jakarta.servlet.http.HttpServletRequest;
import me.list_tw.clicktracker.model.ClickRecord;
import me.list_tw.clicktracker.model.PathStatistics;
import me.list_tw.clicktracker.repository.ClickRecordRepository;
import me.list_tw.clicktracker.repository.PathStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClickService {

    @Autowired
    private ClickRecordRepository clickRecordRepository;

    @Autowired
    private PathStatisticsRepository pathStatisticsRepository;

    public void recordClick(HttpServletRequest request, String path) {
        String ipAddress = getClientIp(request);
        String region = request.getHeader("X-Region");
        String device = request.getHeader("User-Agent");
        String browser = request.getHeader("User-Agent");

        ClickRecord clickRecord = new ClickRecord();
        clickRecord.setIpAddress(ipAddress);
        clickRecord.setRegion(region);
        clickRecord.setDevice(device);
        clickRecord.setBrowser(browser);
        clickRecord.setPath(path);

        clickRecordRepository.save(clickRecord);

        List<PathStatistics> statsList = pathStatisticsRepository.findByPath(path);
        PathStatistics stats;
        if (statsList.isEmpty()) {
            stats = new PathStatistics();
            stats.setPath(path);
            stats.setTotalClicks(1L);
            stats.setUniqueClicks(1L);
        } else {
            stats = statsList.get(0);
            stats.setTotalClicks(stats.getTotalClicks() + 1);
            if (isUniqueVisitor(ipAddress, path)) {
                stats.setUniqueClicks(stats.getUniqueClicks() + 1);
            }
        }
        pathStatisticsRepository.save(stats);
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    private boolean isUniqueVisitor(String ipAddress, String path) {
        List<ClickRecord> records = clickRecordRepository.findByIpAddressAndPath(ipAddress, path);
        return records.isEmpty();
    }

}
