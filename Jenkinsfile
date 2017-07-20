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

        stage("Build Purchase Order Service") {
            sh "mvn -f cool-erp/purchase-order/pom.xml clean install"
        }

        stage("Build Sale Service") {
            sh "mvn -f cool-erp/sale/pom.xml clean install"
        }
        
        stage("Build Inventory Service") {
            sh "mvn -f cool-erp/inventory/pom.xml clean install"
        }
        
        stage("Build Product Service") {
            sh "mvn -f cool-erp/product/pom.xml clean install"
        }

        stage("Build Config server") {
            sh "mvn -f cool-erp/config-server/pom.xml clean install"
        }
        stage("Build Eureka server") {
            sh "mvn -f cool-erp/eureka-server/pom.xml clean install"
        }
    }

    stage('Build docker images'){
        docker.withServer("unix:///var/run/docker.sock") {
            docker.build("daniellavoie/sopra-reception", "cool-erp/reception")
            docker.build("daniellavoie/sopra-shipping", "cool-erp/shipping")
            docker.build("daniellavoie/sopra-purchase-order", "cool-erp/purchase-order")
            docker.build("daniellavoie/sopra-sale", "cool-erp/sale")
            docker.build("daniellavoie/sopra-inventory", "cool-erp/inventory")
            docker.build("daniellavoie/sopra-product", "cool-erp/product")
            docker.build("daniellavoie/sopra-config-server", "cool-erp/config-server")
            docker.build("daniellavoie/sopra-eureka-server", "cool-erp/eureka-server")

            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'github-daniellavoie', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh "docker login --password=${PASSWORD} --username=${USERNAME}"

                sh "docker tag ${USERNAME}/sopra-reception ${USERNAME}/sopra-reception:staging"
                sh "docker tag ${USERNAME}/sopra-shipping ${USERNAME}/sopra-shipping:staging"
                sh "docker tag ${USERNAME}/sopra-purchase-order ${USERNAME}/sopra-purchase-order:staging"
                sh "docker tag ${USERNAME}/sopra-sale ${USERNAME}/sopra-sale:staging"
                sh "docker tag ${USERNAME}/sopra-inventory ${USERNAME}/sopra-inventory:staging"
                sh "docker tag ${USERNAME}/sopra-product ${USERNAME}/sopra-product:staging"
                sh "docker tag ${USERNAME}/sopra-config-server ${USERNAME}/sopra-config-server:staging"
                sh "docker tag ${USERNAME}/sopra-eureka-server ${USERNAME}/sopra-eureka-server:staging"
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
