package com.flowguard.flowguard.model;

/**
 * Represents a system / application incident.
 * Demo data — not connected to a real incident management tool.
 */
public class Incident {

    private String id;          // e.g. "INC-042"
    private String title;
    private String severity;    // "critical" | "high" | "medium" | "low"
    private String status;      // "open" | "investigating" | "resolved"
    private String reportedAt;  // human-readable timestamp
    private String resolvedAt;  // null / "—" if still open

    public Incident(String id, String title, String severity,
                    String status, String reportedAt, String resolvedAt) {
        this.id = id;
        this.title = title;
        this.severity = severity;
        this.status = status;
        this.reportedAt = reportedAt;
        this.resolvedAt = resolvedAt;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public String getReportedAt() { return reportedAt; }
    public String getResolvedAt() { return resolvedAt; }
}
