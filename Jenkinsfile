pipeline {
    agent any
	
    stages {
        stage('Deploy docker') {
            steps {
              // This step should not normally be used in your script. Consult the inline help for details.
withDockerRegistry(credentialsId: 'docker', url: 'https://index.docker.io/v1/') {
    // some block
}
            }
        }
    }
}