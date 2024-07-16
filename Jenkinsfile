pipeline {
    agent any

    environment {
        USER_NAME = 'anhmai7777'
        BACKEND_NAME = 'app-backend'
        BACKEND_VERSION = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/OWTVN/training-docker-spring.git',
                credentialsId: '8d7bb182-2318-4f9d-ada4-8d40c1ba921a'
            }
        }
        stage('Java version') {
            steps {
              sh "java --version"
            }
        }
        stage('Use Gradle') {
            steps {
                echo 'Permission'
                sh 'chmod +x gradlew'

                echo 'Build'
                sh './gradlew clean build'

                echo 'Test'
                sh './gradlew test'

                echo 'Wrapper'
                sh './gradlew wrapper'
            }
        }
        stage('Build Docker'){
            steps {
//                 echo 'Docker version'
//                 sh 'docker --version'

                withDockerRegistry(credentialsId: 'docker-hub', url: 'https://index.docker.io/v1/') {
                    echo 'Building the image'
                    sh "docker build -t ${BACKEND_NAME} ."

                    sh "docker push ${USER_NAME}/${BACKEND_NAME}:${BACKEND_VERSION}"
                }
            }
        }
//         stage('Deploy'){
//             steps {
//                 echo 'Deploying the container'
//                 sh 'docker container stop mysql ${BACKEND_NAME}'
//                 sh 'docker-compose up -d --build ${BACKEND_NAME}'
//                 sh 'docker-compose up -d --build mysql'
//             }
//         }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}