

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
                bat 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }

     }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}