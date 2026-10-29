# 🚀 FlowGuard — End-to-End CI/CD and Monitoring Platform

FlowGuard is a DevOps project that automates the build, testing, containerization, deployment, and monitoring of a Spring Boot web application using Jenkins, Docker, Kubernetes, Prometheus, and Grafana.

## ✨ Features

- Automated build and testing using Maven
- CI/CD pipeline using Jenkins
- Docker-based application deployment
- Kubernetes deployment and rollout verification
- Application health monitoring with Spring Boot Actuator
- Metrics collection using Prometheus
- Monitoring dashboard using Grafana

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Application development |
| Spring Boot | Backend web application |
| Maven | Build and testing |
| Jenkins | CI/CD automation |
| Docker | Containerization |
| Kubernetes | Container orchestration |
| Prometheus | Metrics collection |
| Grafana | Metrics visualization |
| Git and GitHub | Version control |

## 🏗️ Architecture

Developer → GitHub → Jenkins → Maven Build and Tests → Docker Image → Kubernetes Deployment → Spring Boot Actuator → Prometheus → Grafana Dashboard

## ⚙️ Prerequisites

Install the following tools before running the project:

- Java 21
- Git
- Docker Desktop
- Jenkins
- Kubernetes enabled in Docker Desktop
- Prometheus
- Grafana

## ▶️ Run the Application Locally

Clone the repository:

```bash
git clone https://github.com/srija0811/FlowGuard.git
cd FlowGuard
```

Build and test the application on Windows:

```cmd
mvnw.cmd clean verify
```

Run the generated Spring Boot application using the Maven wrapper:

```cmd
mvnw.cmd spring-boot:run
```

The application uses port `8088` by default.

## 🐳 Docker Deployment

Build the application JAR first, then build the Docker image:

```cmd
mvnw.cmd clean package
docker build -t flowguard:1.0 .
docker run -d --name flowguard-app -p 8089:8088 flowguard:1.0
```

Open the application:

http://localhost:8089

## 💚 Health and Metrics Endpoints

- Health: `http://localhost:8089/actuator/health`
- Prometheus metrics: `http://localhost:8089/actuator/prometheus`

## 📊 Monitoring

Prometheus collects application metrics, and Grafana visualizes them in the **FlowGuard Monitoring** dashboard.

Dashboard panels:

1. FlowGuard Application Uptime
2. FlowGuard JVM Memory Usage
3. FlowGuard CPU Usage

Grafana: http://localhost:3000

Prometheus: http://localhost:9090

## ☸️ Kubernetes

The project includes Kubernetes deployment configuration under the `k8s/` directory.

Verify the deployment with:

```cmd
kubectl get pods
kubectl get services
```

If the application is exposed through a Kubernetes Service, use the configured Service access method.

## 🔄 CI/CD Workflow

1. Push source code to GitHub.
2. Jenkins checks out the repository.
3. Maven builds the application and runs tests.
4. Docker builds the application image.
5. The application is deployed to Docker and Kubernetes according to the pipeline.
6. Health checks verify application availability.
7. Prometheus collects metrics, and Grafana displays monitoring graphs.

## 🎯 Project Objective

The objective of FlowGuard is to demonstrate an end-to-end DevOps workflow that combines CI/CD automation, containerization, Kubernetes deployment, health checks, and application monitoring in a local development environment.

## 👩‍💻 Author

**Srija**

GitHub: https://github.com/srija0811
