def call () {
    pipeline {
        agent any
        environment {
            VERSION = "23.4.0"
            DOCKER_CREDS = credentials('docker-hub')
        }
        stages {
            stage('build') {
                steps{
                    script {
                        echo "building"
                        echo "Build number is:${BUILD_NUMBER}"
                        echo "Branch name is: ${BRANCH_NAME}"
                        echo "VERSION is: ${VERSION}"
                        echo "DOCKER_USERNAME is: ${DOCKER_CREDS_USR}"
                        echo "DOCKER_USERNAME is: ${DOCKER_CREDS_PSW}"
                    }
                }

            }
            stage('test') {
                steps{
                    script {
                        echo "testing"
                    }
                }
            }
        }
    }
}