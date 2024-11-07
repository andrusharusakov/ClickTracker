package me.list_tw.clicktracker.repository;

import me.list_tw.clicktracker.model.ClickRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClickRecordRepository extends JpaRepository<ClickRecord, Long> {
    Optional<ClickRecord> findByIpAddressAndPath(String ipAddress, String path);
}
