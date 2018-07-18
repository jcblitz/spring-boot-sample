pipeline {
    environment {
    
        PATH = "${tool 'maven'}/bin:${env.PATH}"
        version = "1.0.${env.BUILD_NUMBER}"
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
                sh 'mvn -B -V -U -e clean package'
            }
        }

        stage('Deploy') {
            steps {
                sh "curl -s --upload-file ${WORKSPACE}/target/spring-boot-sample.war --user tomcat:password \"http://localhost:9080/manager/text/deploy?path=/spring-boot-sample&update=true&tag=$version\""
            }
        }
    }

     post {
        always {
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
        }
    }
}