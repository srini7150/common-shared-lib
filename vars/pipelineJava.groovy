def call (Map pipelineParams) {
    pipeline {
        agent any

        parameters {
            string(name: 'launchTests', defaultValue: 'true', description: 'Launch unit tests? Reply true or false - Mandatory for release branches', trim: true)
        }

        // environment {
        //     DOCKER_CREDS = credentials('docker-hub')
        // }

        options {
            skipDefaultCheckout(true)
            buildDiscarder (logRotator(numToKeepStr: '5'))
        }

        stages {
            stage('Initialization') {
                steps {
                    script {
                        checkoutScm()
                    }
                }
            }
            stage('build') {
                steps{
                    script {
                        def release_branch = getReleaseBranch()
                        echo "release branch is ${release_branch}"
                        def VERSION = godVersion(pipelineParams.module, BRANCH_NAME)
                        echo "branch is: ${BRANCH_NAME}"
                        echo "version is:${VERSION}"
                    }
                }
            }
            stage('test') {
                when {
                    expression { params.launchTests == "true" }
                }
                // agent {
                //     docker {
                //         image 'node:16-alpine'
                //     }
                // }
                steps{
                    script {
                        echo "testing"
                        // sh 'node --version'
                    }
                }
            }

            stage ('auto version increment'){
                when {
                    anyOf {
                        branch "${release_branch}"
                    }
                }
                steps {
                    script {
                        if ("${BRANCH_NAME} == ${release_branch}") {
                            incrementVersionCounter(pipelineParams.module);
                        }
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