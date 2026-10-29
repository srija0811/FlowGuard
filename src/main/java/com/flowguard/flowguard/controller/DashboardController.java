package com.flowguard.flowguard.controller;

import com.flowguard.flowguard.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * DashboardController handles HTTP GET requests and passes data
 * to the Thymeleaf HTML template (dashboard.html).
 *
 * Spring's @Controller annotation tells Spring Boot this class
 * handles web requests. @GetMapping maps a URL path to a method.
 */
@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    // Spring automatically injects DashboardService here (Dependency Injection)
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Handles GET /  and GET /dashboard
     * Populates the Thymeleaf Model with all dashboard sections,
     * then returns "dashboard" which Thymeleaf resolves to
     * src/main/resources/templates/dashboard.html
     */
    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model) {

        // ── Real data from Actuator ──────────────────────────────────────────
        model.addAttribute("appHealth",     dashboardService.getAppHealthStatus());

        // ── Demo data sections ───────────────────────────────────────────────
        model.addAttribute("pipelineStages",    dashboardService.getPipelineStages());
        model.addAttribute("deploymentHistory", dashboardService.getDeploymentHistory());
        model.addAttribute("containers",        dashboardService.getContainerStatus());
        model.addAttribute("incidents",         dashboardService.getIncidents());

        // ── Summary counts for stat cards ────────────────────────────────────
        model.addAttribute("runningContainers",       dashboardService.countRunningContainers());
        model.addAttribute("openIncidents",           dashboardService.countOpenIncidents());
        model.addAttribute("successfulDeployments",   dashboardService.countSuccessfulDeployments());

        return "dashboard"; // resolves to templates/dashboard.html
    }
}
