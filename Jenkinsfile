pipeline {

	environment {
        PATH = "$PATH:/var/jenkins_home/workspace/shopping-server-pipeline/docker-compose.yml"
    }
	
    stages {
        stage('Deploy docker') {
            steps {
              sh "chmod u+x docker-compose.yml"
              sh "docker-compose up"
            }
        }
    }
}