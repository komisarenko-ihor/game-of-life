pipeline {
        agent any
        stages {
                stage("build") {
                        steps {
                                echo 'building the application ...'
                                echo 'some changes ...'
                                withGradle() {
                                        bash './gradlew -v'
                                }
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
