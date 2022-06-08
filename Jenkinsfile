pipeline {
    agent any

    stages {
        stage('Deploy docker') {
            steps {
                sh "docker-compose up"
            }
        }
    }
}