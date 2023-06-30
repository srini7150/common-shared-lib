def call () {
    pipeline {
        agent any
        environment {
            DOCKER_CREDS = credentials('docker-hub')
        }
        stages {
            stage('build') {
                steps{
                    script {
                        echo "building"
                        echo "Build number is:${BUILD_NUMBER}"
                        echo "Branch name is: ${BRANCH_NAME}"
                        def VERSION = readFile('version.counter')
                        echo "version is: ${VERSION}"
                        echo "DOCKER_USERNAME is: ${DOCKER_CREDS_USR}"
                        echo "DOCKER_USERNAME is: ${DOCKER_CREDS_PSW}"
                    }
                }
            }
            stage('test') {
                agent {
                    docker {
                        image 'node:16-alpine'
                    }
                }
                steps{
                    script {
                        echo "testing"
                        sh 'node --version'
                    }
                }
            }
        }
    }
}