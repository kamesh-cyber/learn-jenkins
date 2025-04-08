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
        stage('Test') {
            steps {
                script {
                    bat "mvn test"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    bat "docker stop my-java-app-${DEPLOY_ENV} || exit 0"
                    bat "docker rm my-java-app-${DEPLOY_ENV} || exit 0"
                    if (DEPLOY_ENV == 'staging') {
                        bat "docker run -d -p 8081:8081 --name my-java-app-${DEPLOY_ENV} my-java-app"
                    } else {
                        bat "docker run -d -p 8082:8082 --name my-java-app-${DEPLOY_ENV} my-java-app"
                    }
                }
            }
        }
    }
}
