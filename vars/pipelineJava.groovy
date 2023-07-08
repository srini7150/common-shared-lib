def call () {
    pipeline {
        agent any

        parameters {
            string(name: 'launchTests', defaultValue: 'true', description: 'Launch unit tests? Reply true or false - Mandatory for release branches', trim: true)
        }

        environment {
            DOCKER_CREDS = credentials('docker-hub')
        }

        options {
            buildDiscarder (logRotator(numToKeepStr: '5'))
        }

        stages {
            stage('build') {
                steps{
                    script {
                        // def version_vars = readProperties  file:'version_vars.properties'
                        // def VERSION = version_vars['VERSION']
                        // def INT_RELEASE_BUILD_NO = version_vars['initial_release_build_number']
                        // def UNIQUE_VERSION_ID = BUILD_NUMBER.toInteger() - INT_RELEASE_BUILD_NO.toInteger()
                        // echo "version is: ${VERSION}-${UNIQUE_VERSION_ID}"
                        // echo "DOCKER_USERNAME is: ${DOCKER_CREDS_USR}"
                        // echo "DOCKER_USERNAME is: ${DOCKER_CREDS_PSW}"
                        def VERSION = godGetVersion(pipelineParams.module, BRANCH_NAME)
                        echo "branch is: ${BRANCH_NAME}"
                        echo "version is:${VERSION}"
                    }
                }
            }
            stage('test') {
                when {
                    expression { params.launchTests == "true" }
                }
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