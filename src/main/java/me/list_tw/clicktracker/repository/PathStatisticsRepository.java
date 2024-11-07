package me.list_tw.clicktracker.repository;

import me.list_tw.clicktracker.model.PathStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathStatisticsRepository extends JpaRepository<PathStatistics, Long> {
    List<PathStatistics> findByPath(String path);
}
