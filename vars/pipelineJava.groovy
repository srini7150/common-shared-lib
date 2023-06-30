def call () {
    pipeline {
        agent any
        environment {
            VERSION = "23.4.0"
        }
        stages {
            stage('build') {
                steps{
                    script {
                        echo "building"
                        echo "Build number is:${BUILD_NUMBER}"
                        echo "Branch name is: ${BRANCH_NAME}"
                        echo "VERSION is: ${VERSION}"
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