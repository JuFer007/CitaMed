pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'cristiaann19' 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Backend - Build & Test') {
            agent {
                docker { 
                    image 'maven:3.9.6-eclipse-temurin-21' 
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                dir('backend') {
                    echo 'Compilando el Backend (Spring Boot con Java 21)...'
                    sh 'mvn clean package -DskipTests=false'
                }
            }
        }

        stage('Frontend - Build & Test') {
            agent {
                docker { image 'node:18-alpine' }
            }
            steps {
                dir('frontend') {
                    echo 'Instalando dependencias y compilando Frontend (Angular)...'
                    sh 'npm install'
                    sh 'npm run build -- --configuration=production'
                }
            }
        }
    }

    post {
        success {
            echo '¡El Pipeline finalizó con éxito! 🚀'
        }
        failure {
            echo 'Hubo un error en la construcción. Revisa los logs de Jenkins. ❌'
        }
    }
}