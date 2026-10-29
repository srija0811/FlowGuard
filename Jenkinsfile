
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
                bat '"C:\\Users\\ADMIN\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t flowguard:1.0 .'
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
    }

    post {
        success {
            echo 'FlowGuard CI/CD pipeline completed successfully!'
            echo 'Application URL: http://localhost:8089'
        }

        failure {
            echo 'FlowGuard pipeline failed. Check Console Output.'
        }
    }
}
