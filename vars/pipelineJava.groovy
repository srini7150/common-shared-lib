def call () {
    pipeline {
        agent any
        stages {
            stage('build') {
                steps{
                    script {
                        echo "building"
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