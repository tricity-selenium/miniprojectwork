pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps { git url: 'https://github.com/tricity-selenium/miniprojectwork.git', branch: 'main' }
    }
    stage('Build & Test') {
      steps { bat 'mvn clean test' }
    }
  }
post {
      always {junit 'target/surefire-reports/*.xml'}
     }
  
}