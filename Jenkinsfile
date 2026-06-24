pipeline {
    agent {
        label 'master' || 'built-in'
    }

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
                // Usamos una imagen oficial de Maven optimizada con Java 21
                docker { 
                    image 'maven:3.9.6-eclipse-temurin-21' 
                    args '-v $HOME/.m2:/root/.m2' // Caché para acelerar futuras descargas
                }
            }
            steps {
                dir('backend') {
                    echo 'Compilando el Backend (Spring Boot con Java 21)...'
                    
                    // Si usas MAVEN, descomenta la siguiente línea:
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

        stage('Dockerize & Push (Opcional)') {
            steps {
                echo 'Etapa lista para construir tus imágenes de Docker si lo deseas.'
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