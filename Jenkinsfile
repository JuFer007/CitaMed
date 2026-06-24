pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Backend - Build & Test') {
            steps {
                dir('backend') {
                    echo 'Compilando el Backend (Spring Boot con Java 21)...'
                    // Usamos el wrapper de Maven (mvnw) o Gradle (gradlew) que viene en tu proyecto.
                    // Esto descarga automáticamente la versión correcta de Java/Maven sin pedirle nada a Docker.
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests=false'
                }
            }
        }

        stage('Frontend - Build & Test') {
            steps {
                dir('frontend') {
                    echo 'Instalando dependencias y compilando Frontend (Angular)...'
                    // Usamos comandos globales. Nota: Tu contenedor de Jenkins debe tener Node instalado, 
                    // o puedes configurar Node en "Manage Jenkins -> Tools" y llamarlo aquí.
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