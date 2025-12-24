pipeline {
    agent any

    stages {

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
        
        stage('Clean Workspace') {
    steps {
        cleanWs()
    }
}

        stage('Publish TestNG Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'TestNG HTML Report'
                ])
            }
        }
    }
    
    post {
        always {
            
            junit 'target/surefire-reports/*.xml'
        }

        failure {
            echo 'Tests failed — Jenkins build marked as FAILED'
        }
    }
}
