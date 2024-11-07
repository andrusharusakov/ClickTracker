package me.list_tw.clicktracker.repository;

import me.list_tw.clicktracker.model.PathStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathStatisticsRepository extends JpaRepository<PathStatistics, Long> {
    PathStatistics findByPath(String path);
}
