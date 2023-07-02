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
                        def version_vars = readProperties  file:'version_vars.properties'
                        def VERSION = version_vars['VERSION']
                        def INT_RELEASE_BUILD_NO = version_vars['initial_release_build_number']
                        def UNIQUE_VERSION_ID = BUILD_NUMBER.toInteger() - INT_RELEASE_BUILD_NO.toInteger()
                        echo "version is: ${VERSION}-${UNIQUE_VERSION_ID}"
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
            stage('only_hotfix') {
                when {
                    expression {
                        BRANCH_NAME =~ /(hotfix.*)/
                    }
                }
                steps{
                    script {
                        echo "This branch name is: ${BRANCH_NAME}"
                    }
                }
            }
        }
    }
}