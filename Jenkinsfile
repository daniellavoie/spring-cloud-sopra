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

        stage("Build Shipping Service") {
            sh "mvn -f cool-erp/shipping/pom.xml clean install"
        }

        stage("Build Reception Service") {
            sh "mvn -f cool-erp/purchase-order/pom.xml clean install"
        }

	stage("Build Inventory Service") {
            sh "mvn -f cool-erp/inventory/pom.xml clean install"
        }
    }

    stage('Build docker images'){
        docker.withServer("unix:///var/run/docker.sock") {
            docker.build("daniellavoie/sopra-reception", "cool-erp/reception")
            docker.build("daniellavoie/sopra-shipping", "cool-erp/shipping")
            docker.build("daniellavoie/sopra-purchase-order", "cool-erp/purchase-order")
	    docker.build("daniellavoie/sopra-inventory", "cool-erp/inventory")

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'github-daniellavoie', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh "docker login --password=${PASSWORD} --username=${USERNAME}"

                sh "docker tag ${USERNAME}/sopra-reception ${USERNAME}/sopra-reception:staging"
                sh "docker tag ${USERNAME}/sopra-shipping ${USERNAME}/sopra-shipping:staging"
                sh "docker tag ${USERNAME}/sopra-purchase-order ${USERNAME}/sopra-purchase-order:staging"
                sh "docker tag ${USERNAME}/sopra-inventory ${USERNAME}/sopra-inventory:staging"
            }
        }
    }

    stage('Deploy Staging environment') {
        try {
            sh "/usr/local/bin/docker-compose -f cool-erp/docker-compose/sopra-staging/docker-compose.yml down"
        } catch(e){ }

        sh "/usr/local/bin/docker-compose -f cool-erp/docker-compose/sopra-staging/docker-compose.yml up -d"
    }
}
