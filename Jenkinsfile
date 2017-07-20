node {
    withMaven(
        maven: 'maven-3',
        mavenLocalRepo: '.repository'
    ) {
        stage("Checkout SCM") {
            checkout scm
        }

        stage("Build Reception Service") {
            sh "mvn -f cool-erp/reception/pom.xml clean install"
        }
    }

    stage('Build docker images'){
        docker.withServer("unix:///var/run/docker.sock") {
            docker.build("daniellavoie/sopra-reception", "cool-erp/reception")
        }
    }

    stage('Deploy Staging environment') {
        try {
            sh "/usr/local/bin/docker-compose -f cool-erp/docker-compose/staging/docker-compose.yml down"
        } catch(e){ }

        sh "/usr/local/bin/docker-compose -f cool-erp/docker-compose/staging/docker-compose.yml up -d"
    }
}