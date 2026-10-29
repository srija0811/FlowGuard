package com.flowguard.flowguard.model;

/**
 * Represents a running Docker / container service.
 * Demo data — not connected to a real container runtime.
 */
public class ContainerInfo {

    private String name;      // e.g. "flowguard-api"
    private String image;     // e.g. "flowguard/api:1.3.2"
    private String status;    // "running" | "stopped" | "restarting"
    private String uptime;    // e.g. "3 days, 4 hours"
    private int cpuPercent;   // simulated CPU %
    private int memoryMb;     // simulated memory usage in MB

    public ContainerInfo(String name, String image, String status,
                         String uptime, int cpuPercent, int memoryMb) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.uptime = uptime;
        this.cpuPercent = cpuPercent;
        this.memoryMb = memoryMb;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getStatus() { return status; }
    public String getUptime() { return uptime; }
    public int getCpuPercent() { return cpuPercent; }
    public int getMemoryMb() { return memoryMb; }
}
