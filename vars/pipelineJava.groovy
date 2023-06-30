def call () {
    pipeline {
        agent any
        stages {
            stage('build') {
                script {
                    echo "building"
                }
            }
            stage('test') {
                script {
                    echo "tesing"
                }
            }
        }
    }
}