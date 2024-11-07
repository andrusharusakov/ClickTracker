package me.list_tw.clicktracker.repository;

import me.list_tw.clicktracker.model.ClickRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRecordRepository extends JpaRepository<ClickRecord, Long> {
    // No additional methods needed
}
