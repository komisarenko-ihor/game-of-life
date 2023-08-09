pipeline {
        agent any
        tools {
                maven 'Maven 3.9.4'
        }
        stages {
                stage("build") {
                        steps {
                                echo 'building the application ...'
                                echo 'some changes ...'
                                sh 'mvn --version'
                        }
                }
                stage("test") {
                        steps {
                                echo 'testing the application ...'
                        }
                }
                stage("deploy") {
                        steps {
                                echo 'deploying the application ...'
                        }
                }
        }
}
