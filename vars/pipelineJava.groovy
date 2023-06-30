def call () {
    pipeline {
        agent any
        stages {
            stage('build') {
                steps{
                    script {
                        echo "building"
                        echo "Build number is:${BUILD_NUMBER}"
                        echo "Branch name is: ${BRANCH_NAME}"
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