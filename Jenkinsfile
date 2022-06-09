pipeline {
    agent any
	
    stages {
        stage('Build') { 
            steps {
                sh 'mvn clean install -f shopping-server/pom.xml'
            }
        }
    }
}