pipeline {
    agent any

    stages {
        
        stage 'Checkout'{
            nodes{
                node {
                    echo 'Checkout'
                    checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[credentialsId: 'c4534404-5d07-426a-a0ec-d3ef45fd9607', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: 'https://dev.dawan.fr/usvn/svn/test/trunk/sampleMavenProject@Head']], workspaceUpdater: [$class: 'UpdateUpdater']])
                    stash excludes: 'target/*', name: 'couroucoucou'

                }
            }
        }

        stage 'Build' {
            nodes {
                node {
                    unstash 'couroucoucou'
                    build 'Sample Project for training'
                }
            }
        }

        stage "Test" {
            parallel 'testIE' : {
                node {
                    echo 'Test'
                }
            }, 'testFirefox':{
               node {
                    echo 'Test'
                } 
            }
        }


        stage 'Package' {
            nodes {
                
                node {
                    echo 'Paquetage'
                }
            }
        }

        stage 'Deploy' {
            
            input 'Go to Prod'
            node {
                echo 'Déploiement'
            }
        }
    }
}
