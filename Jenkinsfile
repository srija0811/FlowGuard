
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvnw.cmd -B clean verify'
            }
        }

        stage('Package') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t flowguard:1.0 .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                bat '''
                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" rm -f flowguard-app 2>NUL
                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" run -d --name flowguard-app -p 8089:8088 flowguard:1.0
                '''
            }
        }

        stage('Health Check') {
            steps {
                bat '''
                    powershell -NoProfile -Command "$ok = $false; for ($i = 0; $i -lt 10; $i++) { try { $r = Invoke-WebRequest -Uri 'http://localhost:8089/actuator/health' -UseBasicParsing -TimeoutSec 5; if ($r.StatusCode -eq 200) { $ok = $true; Write-Host $r.Content; break } } catch { Start-Sleep -Seconds 3 } }; if (-not $ok) { Write-Error 'Health check failed'; exit 1 }"
                '''
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                bat '''
                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" config current-context

                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" get nodes

                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" apply --validate=false -f k8s\\deployment.yaml

                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" rollout status deployment/flowguard --timeout=120s

                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" get pods

                    "C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\kubectl.exe" --kubeconfig="C:\\Users\\ADMIN\\.kube\\config" get services
                '''
            }
        }
    }

    post {
        success {
            echo 'FlowGuard CI/CD pipeline completed successfully!'
            echo 'Docker application URL: http://localhost:8089'
            echo 'Kubernetes application URL: http://localhost:30088'
            echo 'If NodePort is inaccessible, use kubectl port-forward.'
        }

        failure {
            echo 'FlowGuard pipeline failed. Check Console Output.'
        }
    }
}
