pipeline {

    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "/usr/local/bin:${env.PATH}"

        SONARQUBE_SERVER = 'SonarQubeServer' // SonarQube server name in Jenkins config
        SONAR_TOKEN = 'SONAR_TOKEN' // SONAR_TOKEN=ID in Jenkins credentials, using Secret text as Secret=your_sonar_token
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'vickneee/sept2_week5_inclass_s2'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vickneee/sept2_week5_inclass_s2.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv("${env.SONARQUBE_SERVER}") {
                        // First line is Mac local sonar-scanner path -> use correct path
                        sh """
                            /usr/local/sonarscanner/bin/sonar-scanner \
                            -Dsonar.projectKey=Week5 \
                            -Dsonar.sources=src \
                            -Dsonar.projectName=Week5 \
                            -Dsonar.host.url=http://localhost:9000 \
                            -Dsonar.token=${env.SONAR_TOKEN} \
                            -Dsonar.java.binaries=target/classes \
                        """
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                    // Or specify Dockerfile path explicitly if needed
                    // docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}", "-f ./Dockerfile .")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker push $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG
                    '''
                }
            }
        }
    }
}
