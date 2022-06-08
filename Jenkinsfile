pipeline {
    agent any

    stages {
        stage('Deploy docker') {
            steps {
              sh "chmod u+x docker-compose.yml"
              sh "docker-compose up"
            }
        }
    }
}