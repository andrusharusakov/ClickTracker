package me.list_tw.clicktracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "path_statistics")
public class PathStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private Long totalClicks = 0L;
    private Long uniqueClicks = 0L;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Long getTotalClicks() { return totalClicks; }
    public void setTotalClicks(Long totalClicks) { this.totalClicks = totalClicks; }
    public Long getUniqueClicks() { return uniqueClicks; }
    public void setUniqueClicks(Long uniqueClicks) { this.uniqueClicks = uniqueClicks; }
}
