pipeline {
    agent any
    environment {
        DEPLOY_ENV = ''
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -Dspring.profiles.active=stage'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy to Staging') {
            when {
                not { branch 'main' }
            }
            environment {
                DEPLOY_ENV = 'staging'
            }
            steps {
                sh 'docker build -t hello-world-ui:stage .'
                sh 'docker run -d -p 8081:8080 --name hello-world-ui-stage hello-world-ui:stage'
            }
        }
        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            environment {
                DEPLOY_ENV = 'production'
            }
            steps {
                sh 'docker build -t hello-world-ui:prod .'
                sh 'docker run -d -p 8080:8080 --name hello-world-ui-prod hello-world-ui:prod'
            }
        }
    }
    post {
        success {
            echo "Deployment successful on $DEPLOY_ENV environment."
        }
        failure {
            echo "Deployment failed on $DEPLOY_ENV environment."
        }
    }
}