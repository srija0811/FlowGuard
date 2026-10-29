package com.flowguard.flowguard.model;

/**
 * Represents a past deployment event.
 * Demo data — not connected to a real deployment system.
 */
public class DeploymentRecord {

    private String version;      // e.g. "v1.3.2"
    private String environment;  // "Production" | "Staging" | "Dev"
    private String deployedAt;   // human-readable timestamp string
    private String status;       // "success" | "failed" | "rolled-back"
    private String deployedBy;

    public DeploymentRecord(String version, String environment,
                            String deployedAt, String status, String deployedBy) {
        this.version = version;
        this.environment = environment;
        this.deployedAt = deployedAt;
        this.status = status;
        this.deployedBy = deployedBy;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getVersion() { return version; }
    public String getEnvironment() { return environment; }
    public String getDeployedAt() { return deployedAt; }
    public String getStatus() { return status; }
    public String getDeployedBy() { return deployedBy; }
}
