pipeline {
    agent any

    tools {
        maven 'Maven-3.9 (or anything you want)'
        jdk 'JDK17'
    }

    environment {
        DOCKER_CRED = credentials('dockerhub-cred')
    }

    stages {

        stage('Checkout Code') {
            steps {
            }
        }

        stage('Build Spring Boot App') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                    docker build -t ${DOCKER_CRED_USR}/hospital-app:latest .
                """
            }
        }

        stage('Login to DockerHub') {
            steps {
                sh """
                    echo ${DOCKER_CRED_PSW} | docker login -u ${DOCKER_CRED_USR} --password-stdin
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                sh """
                    docker push ${DOCKER_CRED_USR}/hospital-app:latest
                """
            }
        }

        stage('Run Docker Container') {
            steps {
                sh """
                    docker rm -f hospital-app || true
                    docker run -d --name hospital-app -p 8080:8080 ${DOCKER_CRED_USR}/hospital-app:latest
                """
            }
        }
    }

    post {
        success {
            echo "🚀 Application Running at: http://localhost:8080/index.html"
        }
        failure {
            echo "❌ Build Failed"
        }
    }
}
