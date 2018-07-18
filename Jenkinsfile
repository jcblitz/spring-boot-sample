node {
    stage('Configure') {
        env.PATH = "${tool 'maven'}/bin:${env.PATH}"
        version = '1.0.' + env.BUILD_NUMBER
        currentBuild.displayName = version

        properties([
                buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')),
                [$class: 'GithubProjectProperty', displayName: '', projectUrlStr: 'https://github.com/jcblitz/spring-boot-sample/'],
                pipelineTriggers([[$class: 'GitHubPushTrigger']])
            ])
    }

    stage('Checkout') {
        git 'https://github.com/jcblitz/spring-boot-sample.git'
    }

    stage('Version') {
        sh "echo \'\ninfo.build.version=\'$version >> src/main/resources/application.properties || true"
        sh "mvn -B -V -U -e versions:set -DnewVersion=$version"
    }

    stage('Build') {
        sh 'mvn -B -V -U -e clean package'
    }

    stage('Archive') {
        junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
    }

    stage('Deploy') {
        sh "curl -s --upload-file ${WORKSPACE}/target/spring-boot-sample.war --user tomcat:password \"http://localhost:9080/manager/text/deploy?path=/spring-boot-sample&update=true&tag=$version\""
    }
}