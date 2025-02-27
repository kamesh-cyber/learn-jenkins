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
                        env.DEPLOY_ENV = 'production'
                    } else {
                        env.DEPLOY_ENV = 'staging'
                    }
                }
                bat '''
                set DEPLOY_ENV=${DEPLOY_ENV}
                mvn clean package -Dspring.profiles.active=%DEPLOY_ENV%
                '''
                archiveArtifacts artifacts: 'target\\*.jar', fingerprint: true
            }
        }
        stage('Deploy') {
            steps {
                script {
                    bat 'mkdir deployment'
                    bat 'copy target\\*.jar deployment\\'
                    if (DEPLOY_ENV == 'staging') {
                        bat 'start java -jar deployment\\*.jar --server.port=8081'
                    } else {
                        bat 'start java -jar deployment\\*.jar --server.port=8080'
                    }
                }
            }
        }
    }
    post {
        success {
            echo "Deployment successful on ${DEPLOY_ENV} environment."
        }
        failure {
            echo "Deployment failed on ${DEPLOY_ENV} environment."
        }
    }
}
