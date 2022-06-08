pipeline {
    agent any

    stages {
        stage('Deploy docker') {
            steps {
                docker-compose up
            }
        }
    }
}