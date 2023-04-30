pipeline {
  agent any
  stages {
    stage('Step1') {
      steps {
        echo 'Coucou from step1'
      }
    }

    stage('Step 2.1') {
      parallel {
        stage('Step 2.1') {
          steps {
            sh 'echo "Hello toto from step2'
          }
        }

        stage('Step 2.2') {
          steps {
            echo 'Hello from step2.2'
          }
        }

      }
    }

  }
}