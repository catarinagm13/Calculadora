pipeline
{
    agent {
        label "mvn"
    }

    parameters
    {
        // Tem que se ir ao Jenkins > Configure > This project is parameterized. 
        string(name: 'IMAGE_NAME', defaultValue:'java-calculator', description:'Name of the Image')
        string(name: 'JAR_NAME', defaultValue:'calculadora', description:'Name of the .jar file')
        //string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'container_name', description: 'Docker container name')
       // string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')
    }

 
     stages
    {
 
        stage("Build Jar"){
            steps{
                sh 'javac *.java'
                sh 'jar cfe "$JAR_NAME".jar Calculator *.class'

            }
        }

        stage("store artifact on Nexus") {
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'curl-jenkinsfile-uploadArt-nexus', variable: 'USERPASS')]) {
                sh 'curl -v -u "$USERPASS" --upload-file /var/jenkins_home/workspace/java-calculator-nexus/"$JAR_NAME".jar http://nexus:8081/repository/my-raw/'
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
        // Apaga os dados do workspace.
        stage('Stage D - Clean up resources')
        {
            steps
            {
                cleanWs()
            }
        }
        
    }
