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
            parallel (
     "Test IE" : { sh "echo p1; sleep 20s; echo phase1" },
     "Test Firefox" : { sh "echo p2; sleep 40s; echo phase2" }
   )
                 
  
        }


        stage ('Package') {

                
                steps {
                    echo 'Paquetage'
                }
         
        }

        stage ('Deploy') {
            steps {
                echo 'Déploiement'
            }
        }
    }
}
