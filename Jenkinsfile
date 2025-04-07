pipeline {
    agent any
    environment {
        DEPLOY_ENV = ''
    }
    stages {
        stage('Build') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        DEPLOY_ENV = 'production'
                    } else {
                        DEPLOY_ENV = 'staging'
                    }
                }
                bat "docker build --build-arg DEPLOY_ENV=${DEPLOY_ENV} -t my-java-app ."
                
            }
        }
        stage('Deploy') {
            steps {
                script {
                    bat 'docker stop my-java-app || exit 0'
                    bat 'docker rm my-java-app || exit 0'
                    if (DEPLOY_ENV == 'staging') {
                        bat 'docker run -d -p 8081:8081 --name my-java-app my-java-app'
                    } else {
                        bat 'docker run -d -p 8082:8082 --name my-java-app my-java-app'
                    }
                }
            }
        }
    }
}
