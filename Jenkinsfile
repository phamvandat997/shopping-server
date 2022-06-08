pipeline {
    agent any

    stages {
        stage('Deploy docker') {
            steps {
              sh "docker-compose build"
              sh "docker-compose up -d"
            }
        }
    }
}