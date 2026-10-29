
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
    }

    post {
        success {
            echo 'FlowGuard build, tests and Docker image creation completed!'
        }
        failure {
            echo 'Pipeline failed. Check the Console Output.'
        }
    }
}
