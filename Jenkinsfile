pipeline {
    agent any
	
	environment {
        PATH = "$PATH:/usr/local/bin/"
    }
	
    stages {
        stage('Deploy docker') {
            steps {
              sh "docker-compose up"
            }
        }
    }
}