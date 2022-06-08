pipeline {
    agent any
	
	environment {
        PATH = "$PATH:/var/jenkins_home/workspace/shopping-server-pipeline/docker-compose.yml"
    }
	
    stages {
        stage('Deploy docker') {
            steps {
              sh "docker-compose up"
            }
        }
    }
}