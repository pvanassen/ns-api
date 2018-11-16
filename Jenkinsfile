pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk8'
    }
    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }

        stage ('Compile') {
            steps {
                sh 'mvn -B clean package -Dmaven.tests.skip=true'
            }
            always {
                archiveArtifacts artifacts: '/**/target/*.jar', fingerprint: true
            }
        }

        stage ('Test') {
            steps {
                sh 'mvn -B test -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }

        stage ('Verify') {
            steps {
                sh 'mvn -B verify -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit 'target/failsafe-reports/**/*.xml'
                }
            }
        }

        stage ('Sonar') {
             sh 'mvn sonar:sonar'
        }

        stage ('Deploy') {
            parallel {
                stage ('Deploy snapshot') {
                    when {
                        not {
                            branch 'master'
                        }
                    }
                    steps {
                        sh 'mvn deploy -DaltDeploymentRepository=snapshots-paul::http://nexus3.int.paules.nl/repository/snapshots/'
                    }
                }

                stage ('Deploy master') {
                    when {
                        branch 'master'
                    }
                    steps {
                        sh 'mvn deploy -Psonatype-oss-release'
                    }
                }
            }
        }
    }
}