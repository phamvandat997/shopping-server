pipeline {
    agent any
	
    stages {
        stage('Build') { 
            steps {
                sh 'mvn clean install -f /var/jenkins_home/workspace/shopping-server-pipeline/pom.xml'
            }
        }
    }
}