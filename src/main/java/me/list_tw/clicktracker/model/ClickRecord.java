package me.list_tw.clicktracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "click_records")
public class ClickRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;
    private String region;
    private String device;
    private String browser;
    private String path;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getDevice() { return device; }
    public void setDevice(String device) { this.device = device; }
    public String getBrowser() { return browser; }
    public void setBrowser(String browser) { this.browser = browser; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
