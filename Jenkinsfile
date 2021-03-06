pipeline {
    agent any
    parameters {

        string(name: 'IMAGE_NAME', defaultValue:'java-calculator', description:'Name of the Image')

        string(name: 'JAR_NAME', defaultValue:'calculadora', description:'Name of the .jar file')

        //string(name: 'CONTAINER_NAME', defaultValue: 'java-mvn', description:'Docker Container Name')

        //string(name: 'DOCKER_PORT', defaultValue: '3000', description:'Docker Container Host Port')
    }
        
    stages{
        

        //stage("build & SonarQube analysis") {
            //steps {
              //withSonarQubeEnv('sonarqube') {
                //sh 'mvn clean package sonar:sonar -Dsonar.host.url=http://sonarqube:9000'
            //}
        //}
        //}

        stage("Build Jar"){
            steps{
                sh 'javac *.java'
                sh 'jar cfe "$JAR_NAME".jar Calculator *.class'

            }
        }

        stage("store artifact on Nexus") {
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'nexus-credentials', variable: 'USERPASS')]) {
                sh 'curl -v -u "$USERPASS" --upload-file /var/jenkins_home/workspace/Calculadora-nexus/"$JAR_NAME".jar http://nexus:8081/repository/my-raw/'
            }
        }
    }

        stage('Create Docker Image') {
            steps {
                sh 'docker build -t "$IMAGE_NAME":v1.0 .'
            }
        }

        stage('Push Image to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-login-nexus', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                sh 'docker login -u "$USERNAME" -p "$PASSWORD" localhost:8082'
                sh 'docker tag "$IMAGE_NAME":v1.0 localhost:8082/"$IMAGE_NAME":v1.0'
                sh 'docker push localhost:8082/"$IMAGE_NAME":v1.0'

            }
        }
    }
                stage('Clear WorkSpace') {
            steps {
                cleanWs()
            }
        }
    } 
}