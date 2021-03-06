pipeline {
    environment {
        PATH = "${tool 'maven'}/bin:${env.PATH}"
        version = "1.0.${env.BUILD_NUMBER}"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 10, unit: 'MINUTES')
        timestamps()
    }
        
    agent any
    stages {
        stage('Configure') {
            steps {
                script {
                    currentBuild.displayName = version
                }
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/jcblitz/spring-boot-sample.git'
            }
        }

        stage('Version') {
            steps {
                sh "echo \'\ninfo.build.version=\'$version >> src/main/resources/application.properties || true"
                sh "mvn -B -V -U -e versions:set -DnewVersion=$version"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -V -U -e -DskipTests clean package'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }

        stage('Deploy') {
            steps {
                sh "curl -s --upload-file ${WORKSPACE}/target/spring-boot-sample.war --user tomcat:password \"http://localhost:9080/manager/text/deploy?path=/spring-boot-sample&update=true&tag=$version\""
            }
        }

        stage('Smoke Test') {
            steps {
                sh 'echo Running smoke tests...'
            }
            
        }
    }

     post {
        always {
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
        }
    }
}