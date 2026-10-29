package com.flowguard.flowguard.model;

/**
 * Represents a single stage in the CI/CD pipeline.
 * Demo data — no real pipeline integration.
 */
public class PipelineStage {

    private String name;       // e.g. "Build", "Test", "Deploy"
    private String status;     // "success" | "running" | "failed" | "pending"
    private long durationSecs; // how long the stage took in seconds
    private String triggeredBy;

    public PipelineStage(String name, String status, long durationSecs, String triggeredBy) {
        this.name = name;
        this.status = status;
        this.durationSecs = durationSecs;
        this.triggeredBy = triggeredBy;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getName() { return name; }
    public String getStatus() { return status; }
    public long getDurationSecs() { return durationSecs; }
    public String getTriggeredBy() { return triggeredBy; }
}
