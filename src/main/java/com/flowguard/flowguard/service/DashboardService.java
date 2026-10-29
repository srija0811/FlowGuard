package com.flowguard.flowguard.service;

import com.flowguard.flowguard.model.ContainerInfo;
import com.flowguard.flowguard.model.DeploymentRecord;
import com.flowguard.flowguard.model.Incident;
import com.flowguard.flowguard.model.PipelineStage;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DashboardService gathers:
 *   - Real health status from Spring Boot's ApplicationAvailability (Liveness + Readiness)
 *   - Demo (sample) data for CI/CD pipelines, deployments, containers, incidents
 *
 * All lists marked [DEMO DATA] are hard-coded for the college project.
 * In a real DevOps system they would come from a database or external API.
 *
 * NOTE: Spring Boot 4.x removed the old HealthEndpoint public API.
 *       We use ApplicationAvailability instead — it reports liveness (CORRECT/BROKEN)
 *       and readiness (ACCEPTING_TRAFFIC / REFUSING_TRAFFIC) which are the real signals.
 */
@Service
public class DashboardService {

    // Spring Boot's ApplicationAvailability reports real liveness & readiness probes
    private final ApplicationAvailability applicationAvailability;

    public DashboardService(ApplicationAvailability applicationAvailability) {
        this.applicationAvailability = applicationAvailability;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 1. Application health  (real data — liveness + readiness probes)
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Returns a combined status string: "UP", "DOWN", or "DEGRADED".
     *  - "UP"      → liveness CORRECT  + readiness ACCEPTING_TRAFFIC
     *  - "DEGRADED"→ liveness CORRECT  + readiness REFUSING_TRAFFIC  (starting up / shutting down)
     *  - "DOWN"    → liveness BROKEN   (app has crashed internally)
     */
    public String getAppHealthStatus() {
        LivenessState liveness   = applicationAvailability.getLivenessState();
        ReadinessState readiness = applicationAvailability.getReadinessState();

        if (liveness == LivenessState.BROKEN) {
            return "DOWN";
        }
        if (readiness == ReadinessState.ACCEPTING_TRAFFIC) {
            return "UP";
        }
        return "DEGRADED";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 2. CI/CD Pipeline stages  [DEMO DATA]
    // ─────────────────────────────────────────────────────────────────────────

    public List<PipelineStage> getPipelineStages() {
        return List.of(
            new PipelineStage("Source Checkout", "success", 12,  "GitHub Webhook"),
            new PipelineStage("Maven Build",     "success", 87,  "GitHub Webhook"),
            new PipelineStage("Unit Tests",      "success", 54,  "GitHub Webhook"),
            new PipelineStage("Code Analysis",   "success", 33,  "GitHub Webhook"),
            new PipelineStage("Docker Build",    "running", 0,   "GitHub Webhook"),
            new PipelineStage("Deploy Staging",  "pending", 0,   "GitHub Webhook"),
            new PipelineStage("Smoke Tests",     "pending", 0,   "GitHub Webhook"),
            new PipelineStage("Deploy Prod",     "pending", 0,   "GitHub Webhook")
        );
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 3. Deployment history  [DEMO DATA]
    // ─────────────────────────────────────────────────────────────────────────

    public List<DeploymentRecord> getDeploymentHistory() {
        return List.of(
            new DeploymentRecord("v1.4.0", "Production", "29 Oct 2026, 11:15 AM", "success",     "GitHub Actions"),
            new DeploymentRecord("v1.3.9", "Staging",    "28 Oct 2026, 06:30 PM", "success",     "GitHub Actions"),
            new DeploymentRecord("v1.3.8", "Production", "27 Oct 2026, 02:00 PM", "rolled-back", "GitHub Actions"),
            new DeploymentRecord("v1.3.7", "Dev",        "26 Oct 2026, 09:45 AM", "success",     "Manual"),
            new DeploymentRecord("v1.3.6", "Production", "25 Oct 2026, 04:20 PM", "success",     "GitHub Actions"),
            new DeploymentRecord("v1.3.5", "Staging",    "24 Oct 2026, 12:00 PM", "failed",      "GitHub Actions")
        );
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 4. Container status  [DEMO DATA]
    // ─────────────────────────────────────────────────────────────────────────

    public List<ContainerInfo> getContainerStatus() {
        return List.of(
            new ContainerInfo("flowguard-api",      "flowguard/api:1.4.0",      "running",    "3 days, 4h",  12, 256),
            new ContainerInfo("flowguard-frontend", "flowguard/frontend:1.4.0", "running",    "3 days, 4h",   5, 128),
            new ContainerInfo("postgres-db",        "postgres:16-alpine",       "running",    "14 days, 2h",  8, 512),
            new ContainerInfo("redis-cache",        "redis:7-alpine",           "running",    "14 days, 2h",  2,  64),
            new ContainerInfo("nginx-proxy",        "nginx:1.27-alpine",        "running",    "14 days, 2h",  1,  32),
            new ContainerInfo("log-collector",      "fluent/fluentbit:3.1",     "restarting", "0m 43s",      25, 180)
        );
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 5. Incident history  [DEMO DATA]
    // ─────────────────────────────────────────────────────────────────────────

    public List<Incident> getIncidents() {
        return List.of(
            new Incident("INC-047", "Log collector container crash-looping",
                         "high",     "investigating", "29 Oct 2026, 12:30 PM", "—"),
            new Incident("INC-046", "v1.3.8 production rollback — memory leak",
                         "critical", "resolved",      "27 Oct 2026, 02:15 PM", "27 Oct 2026, 03:45 PM"),
            new Incident("INC-045", "Staging DB connection pool exhausted",
                         "medium",   "resolved",      "25 Oct 2026, 08:00 AM", "25 Oct 2026, 09:20 AM"),
            new Incident("INC-044", "CI pipeline timeout on unit-test stage",
                         "low",      "resolved",      "23 Oct 2026, 05:50 PM", "23 Oct 2026, 06:10 PM"),
            new Incident("INC-043", "nginx SSL certificate renewal failure",
                         "high",     "resolved",      "20 Oct 2026, 10:00 AM", "20 Oct 2026, 11:30 AM")
        );
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 6. Summary counts for the stat cards
    // ─────────────────────────────────────────────────────────────────────────

    public long countRunningContainers() {
        return getContainerStatus().stream()
                .filter(c -> "running".equals(c.getStatus())).count();
    }

    public long countOpenIncidents() {
        return getIncidents().stream()
                .filter(i -> !"resolved".equals(i.getStatus())).count();
    }

    public long countSuccessfulDeployments() {
        return getDeploymentHistory().stream()
                .filter(d -> "success".equals(d.getStatus())).count();
    }
}
