pipeline {
    agent any

    stages {
        
        stage ('Checkout'){
            
                steps {
                    echo 'Checkout'
                    checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[credentialsId: 'c4534404-5d07-426a-a0ec-d3ef45fd9607', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: 'https://dev.dawan.fr/usvn/svn/test/trunk/sampleMavenProject@Head']], workspaceUpdater: [$class: 'UpdateUpdater']])
                    stash excludes: 'target/*', name: 'couroucoucou'

                }
            
        }

        stage ('Build') {
            
                steps {
                    unstash 'couroucoucou'
                    build 'Sample Project for training'
                }
          
        }

        stage ('Test') {
            
                    steps {
                    echo 'Test'
                    }
  
        }


        stage ('Package') {

                
                steps {
                    echo 'Paquetage'
                }
         
        }

        stage ('Deploy') {
            
            input ('Go to Prod')
            steps {
                echo 'Déploiement'
            }
        }
    }
}
