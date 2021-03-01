pipeline {
    agent {
        label "mvn"
    }

        parameters { 
        string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'calculador-image', description: 'Docker Image')
        string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'calculator-container', description: 'Docker Container Name')
        string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker Port')

    }

    stages {
        stage('Build Docker Image') { 
            steps {
                sh 'docker build -t "${DOCKER_IMAGE_NAME}" .'
            }
        }   
        stage('Push Docker image') { 
            steps {
                sh 'docker login -u admin -p admin localhost:8082'
                sh 'docker tag ${​​DOCKER_IMAGE_NAME}​​ localhost:8082/"${DOCKER_IMAGE_NAME}"'
                sh 'docker push localhost:8082/"${DOCKER_IMAGE_NAME}"'
                sh 'javac *.java'
                sh 'jar cfe he.jar Calculator *.class'
                sh 'curl -v -u "admin:admin" --upload-file ./target/*.jar http://nexus:8081/repository/my-raw/'
            }
        }   
            stage ('CleanResources') {
                     steps
                     {
                         cleanWs()
                     }
                }
             }
    }