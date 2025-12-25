pipeline {
    agent any

    stages {
    
    stage('Clean Workspace') {
    steps {
        cleanWs()
    }
}

        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/tricity-selenium/miniprojectwork.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
        
    
        stage('Publish TestNG Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG HTML Report',
                    keepAll: true,
    				alwaysLinkToLastBuild: true,
    				allowMissing: false
                ])
            }
        }
    }
    
   
}
